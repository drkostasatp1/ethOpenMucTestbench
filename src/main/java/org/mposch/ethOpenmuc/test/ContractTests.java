package org.mposch.ethOpenmuc.test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.mposch.ethOpenmuc.contracts.ContractBean;
import org.mposch.ethOpenmuc.gui.MainWindow;
import org.mposch.solidityContractWrapper.RecordStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.protocol.core.methods.response.TransactionReceipt;

@Component
public class ContractTests {
	@Autowired
	private ContractBean	cb;
	@Autowired
	private MainWindow		gui;

	public ContractTests() {
		// TODO Auto-generated constructor stub
	}

	public void runAllTests() {

		ExecutorService executorService = Executors.newFixedThreadPool(1);
		executorService.execute(new Runnable() {

			@Override
			public void run() {
				long start=0, end =0;
				while (cb.isValid() != true)
				{
					try
					{
						Thread.sleep(100);
					}
					catch (InterruptedException e)
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				String test = "Roonee4i";
				try
				{
					start = System.currentTimeMillis();
					test1(test, 10);
					// The contract is filled with one single test text.
					test2(test);
					test3();
					test4();
					end = System.currentTimeMillis();
					System.out.println("TEST Finished, runtime = " +(end-start)/1000 +" Seconds.");
				}
				catch (Exception e)
				{
					end = System.currentTimeMillis();
					System.out.println("TEST FAILED, runtime = " +(end-start)/1000 +" Seconds.");
					System.out.println(e.getMessage());
					
				}

			}
		});
	}

	private void test2(String test) throws InterruptedException, ExecutionException, Exception {
		Utf8String result;
		// Iterate over all Contract items and compare them to a String
		int max = cb.getContract().getCount().get().getValue().intValue();
		Address adr;

		for (int i = 0; i < max; i++)
		{
			adr = cb.getContract().getStringAtIndex(new Uint256(i)).get();
			result = cb.getContract().getString(adr).get();

			if (!result.toString().equals(test)) throw new Exception("Iteration Test Failed, strings not equal");

		}
	}

	/**
	 * Test 4, Delete everything in the Contract.
	 * 
	 * @throws InterruptedException
	 * @throws ExecutionException
	 */
	private void test4() throws InterruptedException, ExecutionException {
		Address adr;
		int i = 0;
		RecordStorage contract = cb.getContract();
		// test 4: DELETE EVERYTHING

		while (contract.getCount().get().getValue().intValue() != 0)
		{
			i++;
			adr = contract.getStringAtIndex(new Uint256(0)).get();
			System.out.println("Deleting (" + i + "):" + adr.toString() + ", " + contract.getString(adr).get());
			contract.deleteString(adr).get();
		}

		System.out.println("Contract Items Count: " + contract.getCount().get().getValue().toString());
	}

	// Insert some Values
	private void test3() throws InterruptedException, ExecutionException {
		for (int a = 1; a < 10; a++)
		{
			Address adr;
			TransactionReceipt transactionReceipt;
			adr = new Address(Integer.toString(a));
			String test;
			test = "Test Iteration: " + Integer.toString(a);
			System.out.println(test.toString());

			transactionReceipt = cb.mergeStringBlocking(adr, test);

		}

	}

	private void test1(String test, int max) throws InterruptedException {
		for (int i = 1; i < max; i++)
		{
			Utf8String result;
			RecordStorage contract = cb.getContract();
			Address adr = new Address(Integer.toString(i));
			try
			{
				TransactionReceipt transactionReceipt = null;
				transactionReceipt = cb.mergeStringBlocking(adr, test);
				System.out.println("Transactionhash for insert: " + transactionReceipt.getTransactionHash());
				System.out.println("Trying to retrieve a value from the contract");
				// Call the contract
				result = contract.getString(adr).get();
			}
			catch (Exception e)
			{
				System.out.println("Exception, restarting loop. " + e.getMessage());
				Thread.sleep(200);
				continue;
			}
			if ((result != null) && (result.toString().equals(test)))
			{
				System.out.println("TEST PASSED");
			}
			else
			{
				System.out.println("CONTRACT TEST FAIL.");
			}
			Thread.sleep(300);

		} // end for

	}
}
