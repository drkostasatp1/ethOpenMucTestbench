package org.mposch.solidityContractWrapper;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.Future;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;

/**
 * Auto generated code.<br>
 * <strong>Do not modify!</strong><br>
 * Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>, or {@link org.web3j.codegen.SolidityFunctionWrapperGenerator} to update.
 *
 * <p>Generated with web3j version 2.3.0.
 */
public final class Mortal extends Contract {
    private static final String BINARY = "606060405260008054600160a060020a03191633600160a060020a031617905542600155341561002e57600080fd5b5b6102cc8061003e6000396000f300606060405236156100755763ffffffff7c01000000000000000000000000000000000000000000000000000000006000350416630cb0c7f0811461007a5780631e9bf0da1461009b57806343d726d6146100b05780638da5cb5b146100c5578063a6f9dae1146100f4578063d8270dce14610115575b600080fd5b341561008557600080fd5b610099600160a060020a036004351661013a565b005b34156100a657600080fd5b6100996101bf565b005b34156100bb57600080fd5b610099610215565b005b34156100d057600080fd5b6100d8610241565b604051600160a060020a03909116815260200160405180910390f35b34156100ff57600080fd5b610099600160a060020a0360043516610250565b005b341561012057600080fd5b61012861029a565b60405190815260200160405180910390f35b680ad78ebc5ac6200000348190101561015257600080fd5b6000805473ffffffffffffffffffffffffffffffffffffffff1916600160a060020a0384161790555b5b803411156101b95733600160a060020a03166108fc8234039081150290604051600060405180830381858888f1935050505015156101b957600080fd5b5b5b5050565b600054600160a060020a0390811690331681146101db57600080fd5b60015462375f000142819010156101f157600080fd5b6000805473ffffffffffffffffffffffffffffffffffffffff191690555b5b505b50565b60005433600160a060020a0390811691161461023057600080fd5b600054600160a060020a0316ff5b5b565b600054600160a060020a031681565b600054600160a060020a03908116903316811461026c57600080fd5b6000805473ffffffffffffffffffffffffffffffffffffffff1916600160a060020a0384161790555b5b5050565b600154815600a165627a7a72305820b5da9b422cc344c2bf5fe67b9b5ecf9ef4c61da0be0f3521366e5d1f7796e5440029";

    private Mortal(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    private Mortal(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public Future<TransactionReceipt> forceOwnerChange(Address _newOwner) {
        Function function = new Function("forceOwnerChange", Arrays.<Type>asList(_newOwner), Collections.<TypeReference<?>>emptyList());
        return executeTransactionAsync(function);
    }

    public Future<TransactionReceipt> disown() {
        Function function = new Function("disown", Arrays.<Type>asList(), Collections.<TypeReference<?>>emptyList());
        return executeTransactionAsync(function);
    }

    public Future<TransactionReceipt> close() {
        Function function = new Function("close", Arrays.<Type>asList(), Collections.<TypeReference<?>>emptyList());
        return executeTransactionAsync(function);
    }

    public Future<Address> owner() {
        Function function = new Function("owner", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public Future<TransactionReceipt> changeOwner(Address _newOwner) {
        Function function = new Function("changeOwner", Arrays.<Type>asList(_newOwner), Collections.<TypeReference<?>>emptyList());
        return executeTransactionAsync(function);
    }

    public Future<Uint256> creationTime() {
        Function function = new Function("creationTime", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public static Future<Mortal> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, BigInteger initialWeiValue) {
        return deployAsync(Mortal.class, web3j, credentials, gasPrice, gasLimit, BINARY, "", initialWeiValue);
    }

    public static Future<Mortal> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, BigInteger initialWeiValue) {
        return deployAsync(Mortal.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "", initialWeiValue);
    }

    public static Mortal load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new Mortal(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    public static Mortal load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new Mortal(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }
}
