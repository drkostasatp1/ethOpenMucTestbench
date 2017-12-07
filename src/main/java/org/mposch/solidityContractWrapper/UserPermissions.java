package org.mposch.solidityContractWrapper;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.Future;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Bool;
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
public final class UserPermissions extends Contract {
    private static final String BINARY = "606060405260008054600160a060020a03191633600160a060020a031617905542600155341561002e57600080fd5b5b61004733600164010000000061031c61006582021704565b61005f3360016401000000006103686100b182021704565b5b6100f8565b600054600160a060020a03908116903316811461008157600080fd5b600160a060020a0383166000908152600260205260409020805461ff001916610100841515021790555b5b505050565b600054600160a060020a0390811690331681146100cd57600080fd5b600160a060020a0383166000908152600260205260409020805460ff19168315151790555b5b505050565b61043a806101076000396000f300606060405236156100965763ffffffff7c01000000000000000000000000000000000000000000000000000000006000350416630cb0c7f0811461009b5780631e9bf0da146100bc578063257a1143146100d157806349ea06181461010457806373a920da14610137578063877c12141461015d5780638da5cb5b14610183578063a6f9dae1146101b2578063d8270dce146101d3575b600080fd5b34156100a657600080fd5b6100ba600160a060020a03600435166101f8565b005b34156100c757600080fd5b6100ba61027d565b005b34156100dc57600080fd5b6100f0600160a060020a03600435166102d3565b604051901515815260200160405180910390f35b341561010f57600080fd5b6100f0600160a060020a03600435166102f5565b604051901515815260200160405180910390f35b341561014257600080fd5b6100ba600160a060020a0360043516602435151561031c565b005b341561016857600080fd5b6100ba600160a060020a03600435166024351515610368565b005b341561018e57600080fd5b6101966103af565b604051600160a060020a03909116815260200160405180910390f35b34156101bd57600080fd5b6100ba600160a060020a03600435166103be565b005b34156101de57600080fd5b6101e6610408565b60405190815260200160405180910390f35b680ad78ebc5ac6200000348190101561021057600080fd5b6000805473ffffffffffffffffffffffffffffffffffffffff1916600160a060020a0384161790555b5b803411156102775733600160a060020a03166108fc8234039081150290604051600060405180830381858888f19350505050151561027757600080fd5b5b5b5050565b600054600160a060020a03908116903316811461029957600080fd5b60015462375f000142819010156102af57600080fd5b6000805473ffffffffffffffffffffffffffffffffffffffff191690555b5b505b50565b600160a060020a03811660009081526002602052604090205460ff165b919050565b600160a060020a038116600090815260026020526040902054610100900460ff165b919050565b600054600160a060020a03908116903316811461033857600080fd5b600160a060020a0383166000908152600260205260409020805461ff001916610100841515021790555b5b505050565b600054600160a060020a03908116903316811461038457600080fd5b600160a060020a0383166000908152600260205260409020805460ff19168315151790555b5b505050565b600054600160a060020a031681565b600054600160a060020a0390811690331681146103da57600080fd5b6000805473ffffffffffffffffffffffffffffffffffffffff1916600160a060020a0384161790555b5b5050565b600154815600a165627a7a7230582089a67101f096494d34dace3e4e6b1c2c9acb6a33eed3873a828f91f9ad23ce290029";

    private UserPermissions(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    private UserPermissions(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
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

    public Future<Bool> getPermissionWrite(Address adr) {
        Function function = new Function("getPermissionWrite", 
                Arrays.<Type>asList(adr), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public Future<Bool> getPermissionRead(Address adr) {
        Function function = new Function("getPermissionRead", 
                Arrays.<Type>asList(adr), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public Future<TransactionReceipt> setPermissionRead(Address user, Bool perm) {
        Function function = new Function("setPermissionRead", Arrays.<Type>asList(user, perm), Collections.<TypeReference<?>>emptyList());
        return executeTransactionAsync(function);
    }

    public Future<TransactionReceipt> setPermissionWrite(Address user, Bool perm) {
        Function function = new Function("setPermissionWrite", Arrays.<Type>asList(user, perm), Collections.<TypeReference<?>>emptyList());
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

    public static Future<UserPermissions> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, BigInteger initialWeiValue) {
        return deployAsync(UserPermissions.class, web3j, credentials, gasPrice, gasLimit, BINARY, "", initialWeiValue);
    }

    public static Future<UserPermissions> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, BigInteger initialWeiValue) {
        return deployAsync(UserPermissions.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "", initialWeiValue);
    }

    public static UserPermissions load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new UserPermissions(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    public static UserPermissions load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new UserPermissions(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }
}
