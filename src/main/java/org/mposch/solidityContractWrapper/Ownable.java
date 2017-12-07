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
public final class Ownable extends Contract {
    private static final String BINARY = "606060405260008054600160a060020a03191633600160a060020a031617905542600155341561002e57600080fd5b5b61027a8061003e6000396000f300606060405263ffffffff7c01000000000000000000000000000000000000000000000000000000006000350416630cb0c7f081146100695780631e9bf0da1461008a5780638da5cb5b1461009f578063a6f9dae1146100ce578063d8270dce146100ef575b600080fd5b341561007457600080fd5b610088600160a060020a0360043516610114565b005b341561009557600080fd5b610088610199565b005b34156100aa57600080fd5b6100b26101ef565b604051600160a060020a03909116815260200160405180910390f35b34156100d957600080fd5b610088600160a060020a03600435166101fe565b005b34156100fa57600080fd5b610102610248565b60405190815260200160405180910390f35b680ad78ebc5ac6200000348190101561012c57600080fd5b6000805473ffffffffffffffffffffffffffffffffffffffff1916600160a060020a0384161790555b5b803411156101935733600160a060020a03166108fc8234039081150290604051600060405180830381858888f19350505050151561019357600080fd5b5b5b5050565b600054600160a060020a0390811690331681146101b557600080fd5b60015462375f000142819010156101cb57600080fd5b6000805473ffffffffffffffffffffffffffffffffffffffff191690555b5b505b50565b600054600160a060020a031681565b600054600160a060020a03908116903316811461021a57600080fd5b6000805473ffffffffffffffffffffffffffffffffffffffff1916600160a060020a0384161790555b5b5050565b600154815600a165627a7a723058207e853912374721361952f438cec6faf6a4c3178599ad186b448a8040402947010029";

    private Ownable(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    private Ownable(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
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

    public static Future<Ownable> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, BigInteger initialWeiValue) {
        return deployAsync(Ownable.class, web3j, credentials, gasPrice, gasLimit, BINARY, "", initialWeiValue);
    }

    public static Future<Ownable> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, BigInteger initialWeiValue) {
        return deployAsync(Ownable.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "", initialWeiValue);
    }

    public static Ownable load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new Ownable(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    public static Ownable load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new Ownable(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }
}
