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
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Int256;
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
public final class RangeChecker extends Contract {
    private static final String BINARY = "606060405260008054600160a060020a03191633600160a060020a031617905542600155341561002e57600080fd5b5b5b6100483360016401000000006107f261006782021704565b6100603360016401000000006109116100b382021704565b5b5b6100fa565b600054600160a060020a03908116903316811461008357600080fd5b600160a060020a0383166000908152600260205260409020805461ff001916610100841515021790555b5b505050565b600054600160a060020a0390811690331681146100cf57600080fd5b600160a060020a0383166000908152600260205260409020805460ff19168315151790555b5b505050565b610b3f806101096000396000f300606060405236156101045763ffffffff7c01000000000000000000000000000000000000000000000000000000006000350416630cb0c7f081146101095780630d8e6e2c1461012a5780631a092541146101b55780631e9bf0da14610240578063257a1143146102555780634010d32a1461028857806343d726d6146102a357806349ea0618146102b85780635c52c074146102eb57806373a920da14610310578063788bc78c146103365780637eabd3311461038957806384486d90146103b3578063877c1214146103d85780638da5cb5b146103fe57806390c3f38f1461042d578063a6f9dae114610480578063c82d4c19146104a1578063d8270dce146104c9575b600080fd5b341561011457600080fd5b610128600160a060020a03600435166104ee565b005b341561013557600080fd5b61013d610573565b60405160208082528190810183818151815260200191508051906020019080838360005b8381101561017a5780820151818401525b602001610161565b50505050905090810190601f1680156101a75780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b34156101c057600080fd5b61013d61061c565b60405160208082528190810183818151815260200191508051906020019080838360005b8381101561017a5780820151818401525b602001610161565b50505050905090810190601f1680156101a75780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b341561024b57600080fd5b6101286106c5565b005b341561026057600080fd5b610274600160a060020a036004351661071b565b604051901515815260200160405180910390f35b341561029357600080fd5b61012860043560243561073d565b005b34156102ae57600080fd5b61012861076a565b005b34156102c357600080fd5b610274600160a060020a0360043516610796565b604051901515815260200160405180910390f35b34156102f657600080fd5b6102fe6107bd565b60405190815260200160405180910390f35b341561031b57600080fd5b610128600160a060020a036004351660243515156107f2565b005b341561034157600080fd5b61012860046024813581810190830135806020601f8201819004810201604051908101604052818152929190602084018383808284375094965061083e95505050505050565b005b341561039457600080fd5b610274600435610856565b604051901515815260200160405180910390f35b34156103be57600080fd5b6102fe6108dc565b60405190815260200160405180910390f35b34156103e357600080fd5b610128600160a060020a03600435166024351515610911565b005b341561040957600080fd5b610411610958565b604051600160a060020a03909116815260200160405180910390f35b341561043857600080fd5b61012860046024813581810190830135806020601f8201819004810201604051908101604052818152929190602084018383808284375094965061096795505050505050565b005b341561048b57600080fd5b610128600160a060020a036004351661097f565b005b34156104ac57600080fd5b6102fe6004356109c9565b60405190815260200160405180910390f35b34156104d457600080fd5b6102fe610a5b565b60405190815260200160405180910390f35b680ad78ebc5ac6200000348190101561050657600080fd5b6000805473ffffffffffffffffffffffffffffffffffffffff1916600160a060020a0384161790555b5b8034111561056d5733600160a060020a03166108fc8234039081150290604051600060405180830381858888f19350505050151561056d57600080fd5b5b5b5050565b61057b610a61565b60038054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156106115780601f106105e657610100808354040283529160200191610611565b820191906000526020600020905b8154815290600101906020018083116105f457829003601f168201915b505050505090505b90565b610624610a61565b60048054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156106115780601f106105e657610100808354040283529160200191610611565b820191906000526020600020905b8154815290600101906020018083116105f457829003601f168201915b505050505090505b90565b600054600160a060020a0390811690331681146106e157600080fd5b60015462375f000142819010156106f757600080fd5b6000805473ffffffffffffffffffffffffffffffffffffffff191690555b5b505b50565b600160a060020a03811660009081526002602052604090205460ff165b919050565b600054600160a060020a03908116903316811461075957600080fd5b600583905560068290555b5b505050565b60005433600160a060020a0390811691161461078557600080fd5b600054600160a060020a0316ff5b5b565b600160a060020a038116600090815260026020526040902054610100900460ff165b919050565b33600160a060020a0316600090815260026020526040812054610100900460ff1615156107e957600080fd5b506005545b5b90565b600054600160a060020a03908116903316811461080e57600080fd5b600160a060020a0383166000908152600260205260409020805461ff001916610100841515021790555b5b505050565b600381805161056d929160200190610a73565b505b50565b33600160a060020a0316600090815260026020526040812054610100900460ff16151561088257600080fd5b33600160a060020a0316600090815260026020526040902054610100900460ff1615156108ae57600080fd5b6006548212806108bf575060055482135b156108cc57506000610738565b506001610738565b5b5b5b919050565b33600160a060020a0316600090815260026020526040812054610100900460ff16151561090857600080fd5b506006545b5b90565b600054600160a060020a03908116903316811461092d57600080fd5b600160a060020a0383166000908152600260205260409020805460ff19168315151790555b5b505050565b600054600160a060020a031681565b600481805161056d929160200190610a73565b505b50565b600054600160a060020a03908116903316811461099b57600080fd5b6000805473ffffffffffffffffffffffffffffffffffffffff1916600160a060020a0384161790555b5b5050565b33600160a060020a0316600090815260026020526040812054610100900460ff1615156109f557600080fd5b33600160a060020a0316600090815260026020526040902054610100900460ff161515610a2157600080fd5b610a2a82610856565b15610a36575080610738565b600654821215610a495750600654610738565b50600554610738565b5b5b5b5b919050565b60015481565b60206040519081016040526000815290565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f10610ab457805160ff1916838001178555610ae1565b82800160010185558215610ae1579182015b82811115610ae1578251825591602001919060010190610ac6565b5b50610aee929150610af2565b5090565b61061991905b80821115610aee5760008155600101610af8565b5090565b905600a165627a7a7230582002c5477d9bb79188b917238b607e4e137d6c6fa0e58dbd4983e5948c40e031050029";

    private RangeChecker(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    private RangeChecker(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public Future<TransactionReceipt> forceOwnerChange(Address _newOwner) {
        Function function = new Function("forceOwnerChange", Arrays.<Type>asList(_newOwner), Collections.<TypeReference<?>>emptyList());
        return executeTransactionAsync(function);
    }

    public Future<Utf8String> getVersion() {
        Function function = new Function("getVersion", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public Future<Utf8String> getDescription() {
        Function function = new Function("getDescription", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeCallSingleValueReturnAsync(function);
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

    public Future<TransactionReceipt> setRangeValues(Int256 high, Int256 low) {
        Function function = new Function("setRangeValues", Arrays.<Type>asList(high, low), Collections.<TypeReference<?>>emptyList());
        return executeTransactionAsync(function);
    }

    public Future<TransactionReceipt> close() {
        Function function = new Function("close", Arrays.<Type>asList(), Collections.<TypeReference<?>>emptyList());
        return executeTransactionAsync(function);
    }

    public Future<Bool> getPermissionRead(Address adr) {
        Function function = new Function("getPermissionRead", 
                Arrays.<Type>asList(adr), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public Future<Int256> getHighValue() {
        Function function = new Function("getHighValue", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Int256>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public Future<TransactionReceipt> setPermissionRead(Address user, Bool perm) {
        Function function = new Function("setPermissionRead", Arrays.<Type>asList(user, perm), Collections.<TypeReference<?>>emptyList());
        return executeTransactionAsync(function);
    }

    public Future<TransactionReceipt> setVersion(Utf8String v) {
        Function function = new Function("setVersion", Arrays.<Type>asList(v), Collections.<TypeReference<?>>emptyList());
        return executeTransactionAsync(function);
    }

    public Future<Bool> isValid(Int256 value) {
        Function function = new Function("isValid", 
                Arrays.<Type>asList(value), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public Future<Int256> getLowValue() {
        Function function = new Function("getLowValue", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Int256>() {}));
        return executeCallSingleValueReturnAsync(function);
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

    public Future<TransactionReceipt> setDescription(Utf8String d) {
        Function function = new Function("setDescription", Arrays.<Type>asList(d), Collections.<TypeReference<?>>emptyList());
        return executeTransactionAsync(function);
    }

    public Future<TransactionReceipt> changeOwner(Address _newOwner) {
        Function function = new Function("changeOwner", Arrays.<Type>asList(_newOwner), Collections.<TypeReference<?>>emptyList());
        return executeTransactionAsync(function);
    }

    public Future<Int256> getBoundedValue(Int256 value) {
        Function function = new Function("getBoundedValue", 
                Arrays.<Type>asList(value), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Int256>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public Future<Uint256> creationTime() {
        Function function = new Function("creationTime", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public static Future<RangeChecker> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, BigInteger initialWeiValue) {
        return deployAsync(RangeChecker.class, web3j, credentials, gasPrice, gasLimit, BINARY, "", initialWeiValue);
    }

    public static Future<RangeChecker> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, BigInteger initialWeiValue) {
        return deployAsync(RangeChecker.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "", initialWeiValue);
    }

    public static RangeChecker load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new RangeChecker(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    public static RangeChecker load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new RangeChecker(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }
}
