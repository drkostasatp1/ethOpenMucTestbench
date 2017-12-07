package org.mposch.solidityContractWrapper;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.Future;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
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
public final class Versionable extends Contract {
    private static final String BINARY = "6060604052341561000f57600080fd5b5b61047a8061001f6000396000f300606060405263ffffffff7c01000000000000000000000000000000000000000000000000000000006000350416630d8e6e2c811461005e5780631a092541146100e9578063788bc78c1461017457806390c3f38f146101c7575b600080fd5b341561006957600080fd5b61007161021a565b60405160208082528190810183818151815260200191508051906020019080838360005b838110156100ae5780820151818401525b602001610095565b50505050905090810190601f1680156100db5780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b34156100f457600080fd5b6100716102c3565b60405160208082528190810183818151815260200191508051906020019080838360005b838110156100ae5780820151818401525b602001610095565b50505050905090810190601f1680156100db5780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b341561017f57600080fd5b6101c560046024813581810190830135806020601f8201819004810201604051908101604052818152929190602084018383808284375094965061036c95505050505050565b005b34156101d257600080fd5b6101c560046024813581810190830135806020601f8201819004810201604051908101604052818152929190602084018383808284375094965061038495505050505050565b005b61022261039c565b60008054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156102b85780601f1061028d576101008083540402835291602001916102b8565b820191906000526020600020905b81548152906001019060200180831161029b57829003601f168201915b505050505090505b90565b6102cb61039c565b60018054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156102b85780601f1061028d576101008083540402835291602001916102b8565b820191906000526020600020905b81548152906001019060200180831161029b57829003601f168201915b505050505090505b90565b600081805161037f9291602001906103ae565b505b50565b600181805161037f9291602001906103ae565b505b50565b60206040519081016040526000815290565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f106103ef57805160ff191683800117855561041c565b8280016001018555821561041c579182015b8281111561041c578251825591602001919060010190610401565b5b5061042992915061042d565b5090565b6102c091905b808211156104295760008155600101610433565b5090565b905600a165627a7a72305820f530dc283ad72879900c855d7b6e7aebc2d03f53219243a2030d92c12c513ff30029";

    private Versionable(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    private Versionable(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
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

    public Future<TransactionReceipt> setVersion(Utf8String v) {
        Function function = new Function("setVersion", Arrays.<Type>asList(v), Collections.<TypeReference<?>>emptyList());
        return executeTransactionAsync(function);
    }

    public Future<TransactionReceipt> setDescription(Utf8String d) {
        Function function = new Function("setDescription", Arrays.<Type>asList(d), Collections.<TypeReference<?>>emptyList());
        return executeTransactionAsync(function);
    }

    public static Future<Versionable> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, BigInteger initialWeiValue) {
        return deployAsync(Versionable.class, web3j, credentials, gasPrice, gasLimit, BINARY, "", initialWeiValue);
    }

    public static Future<Versionable> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, BigInteger initialWeiValue) {
        return deployAsync(Versionable.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "", initialWeiValue);
    }

    public static Versionable load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new Versionable(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    public static Versionable load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new Versionable(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }
}
