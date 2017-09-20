package org.mposch.solidityContractWrapper;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Future;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.EventValues;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import rx.Observable;
import rx.functions.Func1;

/**
 * Auto generated code.<br>
 * <strong>Do not modify!</strong><br>
 * Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>, or {@link org.web3j.codegen.SolidityFunctionWrapperGenerator} to update.
 *
 * <p>Generated with web3j version 2.3.0.
 */
public final class RecordStorage extends Contract {
    private static final String BINARY = "606060405260408051908101604052600381527f312e320000000000000000000000000000000000000000000000000000000000602082015260009080516200004d92916020019062000168565b5060018054600160a060020a03191633600160a060020a03161790554260025534156200007957600080fd5b5b60018054620000a391600160a060020a039091169064010000000062000a05620000d382021704565b60018054620000cc91600160a060020a0390911690640100000000620008446200011b82021704565b5b62000212565b600154600160a060020a039081169033168114620000f057600080fd5b600160a060020a0383166000908152600460205260409020805460ff19168315151790555b5b505050565b600154600160a060020a0390811690331681146200013857600080fd5b600160a060020a0383166000908152600460205260409020805461ff001916610100841515021790555b5b505050565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f10620001ab57805160ff1916838001178555620001db565b82800160010185558215620001db579182015b82811115620001db578251825591602001919060010190620001be565b5b50620001ea929150620001ee565b5090565b6200020f91905b80821115620001ea5760008155600101620001f5565b5090565b90565b610f2080620002226000396000f300606060405236156100e35763ffffffff7c010000000000000000000000000000000000000000000000000000000060003504166305dc250681146100e8578063257a1143146101095780633801ebc61461013c57806340194e43146101ad57806349ea0618146101ce57806354fd4d50146102015780635dbe47e81461028c57806373a920da146102bf5780637518e4fe146102e55780637d43442514610317578063877c1214146103ae5780638da5cb5b146103d4578063a87d942c14610403578063d8270dce14610428578063dc8bee921461044d578063f5187dee1461047e575b600080fd5b34156100f357600080fd5b610107600160a060020a03600435166104f1565b005b341561011457600080fd5b610128600160a060020a0360043516610533565b604051901515815260200160405180910390f35b341561014757600080fd5b61019b60048035600160a060020a03169060446024803590810190830135806020601f8201819004810201604051908101604052818152929190602084018383808284375094965061055595505050505050565b60405190815260200160405180910390f35b34156101b857600080fd5b610107600160a060020a03600435166106cb565b005b34156101d957600080fd5b610128600160a060020a036004351661070e565b604051901515815260200160405180910390f35b341561020c57600080fd5b610214610735565b60405160208082528190810183818151815260200191508051906020019080838360005b838110156102515780820151818401525b602001610238565b50505050905090810190601f16801561027e5780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b341561029757600080fd5b610128600160a060020a03600435166107d3565b604051901515815260200160405180910390f35b34156102ca57600080fd5b610107600160a060020a03600435166024351515610844565b005b34156102f057600080fd5b6102fb600435610890565b604051600160a060020a03909116815260200160405180910390f35b341561032257600080fd5b610214600160a060020a03600435166108f3565b60405160208082528190810183818151815260200191508051906020019080838360005b838110156102515780820151818401525b602001610238565b50505050905090810190601f16801561027e5780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b34156103b957600080fd5b610107600160a060020a03600435166024351515610a05565b005b34156103df57600080fd5b6102fb610a4c565b604051600160a060020a03909116815260200160405180910390f35b341561040e57600080fd5b61019b610a5b565b60405190815260200160405180910390f35b341561043357600080fd5b61019b610a90565b60405190815260200160405180910390f35b341561045857600080fd5b61019b600160a060020a0360043516610a96565b60405190815260200160405180910390f35b341561048957600080fd5b61012860048035600160a060020a03169060446024803590810190830135806020601f82018190048102016040519081016040528181529291906020840183838082843750949650610ca995505050505050565b604051901515815260200160405180910390f35b600154600160a060020a03908116903316811461050d57600080fd5b600160a060020a0382166000908152600460205260409020805460ff191690555b5b5050565b600160a060020a03811660009081526004602052604090205460ff165b919050565b600160a060020a03331660009081526004602052604081205460ff16151561057c57600080fd5b610585836107d3565b1561058f57600080fd5b600160a060020a03831660009081526003602052604090208280516105b8929160200190610dee565b506001600580548060010182816105cf9190610e6d565b916000526020600020900160005b8154600160a060020a038089166101009390930a838102910219909116179091556000818152600360205260409081902093909203600193909301839055917f67a48f38e10dc5aac739d90e6c86181e1dec2cfee3269002e83dbbc0941cb2c99185905182815260406020820181815290820183818151815260200191508051906020019080838360005b838110156106815780820151818401525b602001610668565b50505050905090810190601f1680156106ae5780820380516001836020036101000a031916815260200191505b50935050505060405180910390a250600554600019015b92915050565b600154600160a060020a0390811690331681146106e757600080fd5b600160a060020a0382166000908152600460205260409020805461ff00191690555b5b5050565b600160a060020a038116600090815260046020526040902054610100900460ff165b919050565b60008054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156107cb5780601f106107a0576101008083540402835291602001916107cb565b820191906000526020600020905b8154815290600101906020018083116107ae57829003601f168201915b505050505081565b60055460009015156107e757506000610550565b600160a060020a03821660008181526003602052604090206001015460058054909190811061081257fe5b906000526020600020900160005b9054906101000a9004600160a060020a0316600160a060020a03161490505b919050565b600154600160a060020a03908116903316811461086057600080fd5b600160a060020a0383166000908152600460205260409020805461ff001916610100841515021790555b5b505050565b33600160a060020a0316600090815260046020526040812054610100900460ff1615156108bc57600080fd5b60058054839081106108ca57fe5b906000526020600020900160005b9054906101000a9004600160a060020a031690505b5b919050565b6108fb610e97565b33600160a060020a0316600090815260046020526040902054610100900460ff16151561092757600080fd5b610930826107d3565b151561093b57600080fd5b6003600083600160a060020a0316600160a060020a031681526020019081526020016000206000018054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156109f75780601f106109cc576101008083540402835291602001916109f7565b820191906000526020600020905b8154815290600101906020018083116109da57829003601f168201915b505050505090505b5b919050565b600154600160a060020a039081169033168114610a2157600080fd5b600160a060020a0383166000908152600460205260409020805460ff19168315151790555b5b505050565b600154600160a060020a031681565b33600160a060020a0316600090815260046020526040812054610100900460ff161515610a8757600080fd5b506005545b5b90565b60025481565b33600160a060020a031660009081526004602052604081205481908190610100900460ff161515610ac657600080fd5b610acf846107d3565b1515610ada57600080fd5b600160a060020a03841660009081526003602052604090206001015460058054919350906000198101908110610b0c57fe5b906000526020600020900160005b9054906101000a9004600160a060020a0316905080600583815481101515610b3e57fe5b906000526020600020900160005b8154600160a060020a039384166101009290920a9182029184021916179055811660009081526003602052604090206001018290556005805490610b94906000198301610e6d565b5083600160a060020a03167fc081e614396db3b3fb106e28510023a2809ea427581eb82dc77e8c8fe9ead0848360405190815260200160405180910390a2600160a060020a038116600081815260036020526040908190207fa0fe756b7d8c5beeccd8b5643a32e5c78f85181b84521b253c61863dcbbd97e8918591905182815260406020820181815283546002600019610100600184161502019091160491830182905290606083019084908015610c8e5780601f10610c6357610100808354040283529160200191610c8e565b820191906000526020600020905b815481529060010190602001808311610c7157829003601f168201915b5050935050505060405180910390a28192505b5b5050919050565b33600160a060020a0316600090815260046020526040812054610100900460ff161515610cd557600080fd5b610cde836107d3565b1515610ce957600080fd5b600160a060020a0383166000908152600360205260409020828051610d12929160200190610dee565b50600160a060020a0383166000818152600360205260409081902060018101547fa0fe756b7d8c5beeccd8b5643a32e5c78f85181b84521b253c61863dcbbd97e8929091905182815260406020820181815283546002600019610100600184161502019091160491830182905290606083019084908015610dd45780601f10610da957610100808354040283529160200191610dd4565b820191906000526020600020905b815481529060010190602001808311610db757829003601f168201915b5050935050505060405180910390a25060015b5b92915050565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f10610e2f57805160ff1916838001178555610e5c565b82800160010185558215610e5c579182015b82811115610e5c578251825591602001919060010190610e41565b5b50610e69929150610ed3565b5090565b81548183558181151161088a5760008381526020902061088a918101908301610ed3565b5b505050565b60206040519081016040526000815290565b81548183558181151161088a5760008381526020902061088a918101908301610ed3565b5b505050565b610a8c91905b80821115610e695760008155600101610ed9565b5090565b905600a165627a7a72305820735ed916d51512f4f41a3810824d522b1443dd10b1acdaa01d35402e677c83520029";

    private RecordStorage(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    private RecordStorage(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public List<LogNewStringEventResponse> getLogNewStringEvents(TransactionReceipt transactionReceipt) {
        final Event event = new Event("LogNewString", 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Utf8String>() {}));
        List<EventValues> valueList = extractEventParameters(event, transactionReceipt);
        ArrayList<LogNewStringEventResponse> responses = new ArrayList<LogNewStringEventResponse>(valueList.size());
        for (EventValues eventValues : valueList) {
            LogNewStringEventResponse typedResponse = new LogNewStringEventResponse();
            typedResponse.userAddress = (Address) eventValues.getIndexedValues().get(0);
            typedResponse.index = (Uint256) eventValues.getNonIndexedValues().get(0);
            typedResponse.data = (Utf8String) eventValues.getNonIndexedValues().get(1);
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<LogNewStringEventResponse> logNewStringEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        final Event event = new Event("LogNewString", 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Utf8String>() {}));
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(event));
        return web3j.ethLogObservable(filter).map(new Func1<Log, LogNewStringEventResponse>() {
            @Override
            public LogNewStringEventResponse call(Log log) {
                EventValues eventValues = extractEventParameters(event, log);
                LogNewStringEventResponse typedResponse = new LogNewStringEventResponse();
                typedResponse.userAddress = (Address) eventValues.getIndexedValues().get(0);
                typedResponse.index = (Uint256) eventValues.getNonIndexedValues().get(0);
                typedResponse.data = (Utf8String) eventValues.getNonIndexedValues().get(1);
                return typedResponse;
            }
        });
    }

    public List<LogUpdateStringEventResponse> getLogUpdateStringEvents(TransactionReceipt transactionReceipt) {
        final Event event = new Event("LogUpdateString", 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Utf8String>() {}));
        List<EventValues> valueList = extractEventParameters(event, transactionReceipt);
        ArrayList<LogUpdateStringEventResponse> responses = new ArrayList<LogUpdateStringEventResponse>(valueList.size());
        for (EventValues eventValues : valueList) {
            LogUpdateStringEventResponse typedResponse = new LogUpdateStringEventResponse();
            typedResponse.userAddress = (Address) eventValues.getIndexedValues().get(0);
            typedResponse.index = (Uint256) eventValues.getNonIndexedValues().get(0);
            typedResponse.data = (Utf8String) eventValues.getNonIndexedValues().get(1);
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<LogUpdateStringEventResponse> logUpdateStringEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        final Event event = new Event("LogUpdateString", 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Utf8String>() {}));
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(event));
        return web3j.ethLogObservable(filter).map(new Func1<Log, LogUpdateStringEventResponse>() {
            @Override
            public LogUpdateStringEventResponse call(Log log) {
                EventValues eventValues = extractEventParameters(event, log);
                LogUpdateStringEventResponse typedResponse = new LogUpdateStringEventResponse();
                typedResponse.userAddress = (Address) eventValues.getIndexedValues().get(0);
                typedResponse.index = (Uint256) eventValues.getNonIndexedValues().get(0);
                typedResponse.data = (Utf8String) eventValues.getNonIndexedValues().get(1);
                return typedResponse;
            }
        });
    }

    public List<LogDeleteStringEventResponse> getLogDeleteStringEvents(TransactionReceipt transactionReceipt) {
        final Event event = new Event("LogDeleteString", 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        List<EventValues> valueList = extractEventParameters(event, transactionReceipt);
        ArrayList<LogDeleteStringEventResponse> responses = new ArrayList<LogDeleteStringEventResponse>(valueList.size());
        for (EventValues eventValues : valueList) {
            LogDeleteStringEventResponse typedResponse = new LogDeleteStringEventResponse();
            typedResponse.userAddress = (Address) eventValues.getIndexedValues().get(0);
            typedResponse.index = (Uint256) eventValues.getNonIndexedValues().get(0);
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<LogDeleteStringEventResponse> logDeleteStringEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        final Event event = new Event("LogDeleteString", 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(event));
        return web3j.ethLogObservable(filter).map(new Func1<Log, LogDeleteStringEventResponse>() {
            @Override
            public LogDeleteStringEventResponse call(Log log) {
                EventValues eventValues = extractEventParameters(event, log);
                LogDeleteStringEventResponse typedResponse = new LogDeleteStringEventResponse();
                typedResponse.userAddress = (Address) eventValues.getIndexedValues().get(0);
                typedResponse.index = (Uint256) eventValues.getNonIndexedValues().get(0);
                return typedResponse;
            }
        });
    }

    public List<NotifyAllEventResponse> getNotifyAllEvents(TransactionReceipt transactionReceipt) {
        final Event event = new Event("notifyAll", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList());
        List<EventValues> valueList = extractEventParameters(event, transactionReceipt);
        ArrayList<NotifyAllEventResponse> responses = new ArrayList<NotifyAllEventResponse>(valueList.size());
        for (EventValues eventValues : valueList) {
            NotifyAllEventResponse typedResponse = new NotifyAllEventResponse();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<NotifyAllEventResponse> notifyAllEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        final Event event = new Event("notifyAll", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList());
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(event));
        return web3j.ethLogObservable(filter).map(new Func1<Log, NotifyAllEventResponse>() {
            @Override
            public NotifyAllEventResponse call(Log log) {
                EventValues eventValues = extractEventParameters(event, log);
                NotifyAllEventResponse typedResponse = new NotifyAllEventResponse();
                return typedResponse;
            }
        });
    }

    public List<NotifyEventResponse> getNotifyEvents(TransactionReceipt transactionReceipt) {
        final Event event = new Event("notify", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        List<EventValues> valueList = extractEventParameters(event, transactionReceipt);
        ArrayList<NotifyEventResponse> responses = new ArrayList<NotifyEventResponse>(valueList.size());
        for (EventValues eventValues : valueList) {
            NotifyEventResponse typedResponse = new NotifyEventResponse();
            typedResponse.id = (Utf8String) eventValues.getNonIndexedValues().get(0);
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<NotifyEventResponse> notifyEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        final Event event = new Event("notify", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(event));
        return web3j.ethLogObservable(filter).map(new Func1<Log, NotifyEventResponse>() {
            @Override
            public NotifyEventResponse call(Log log) {
                EventValues eventValues = extractEventParameters(event, log);
                NotifyEventResponse typedResponse = new NotifyEventResponse();
                typedResponse.id = (Utf8String) eventValues.getNonIndexedValues().get(0);
                return typedResponse;
            }
        });
    }

    public Future<TransactionReceipt> clearPermissionWrite(Address user) {
        Function function = new Function("clearPermissionWrite", Arrays.<Type>asList(user), Collections.<TypeReference<?>>emptyList());
        return executeTransactionAsync(function);
    }

    public Future<Bool> getPermissionWrite(Address adr) {
        Function function = new Function("getPermissionWrite", 
                Arrays.<Type>asList(adr), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public Future<TransactionReceipt> setString(Address userAddress, Utf8String data) {
        Function function = new Function("setString", Arrays.<Type>asList(userAddress, data), Collections.<TypeReference<?>>emptyList());
        return executeTransactionAsync(function);
    }

    public Future<TransactionReceipt> clearPermissionRead(Address user) {
        Function function = new Function("clearPermissionRead", Arrays.<Type>asList(user), Collections.<TypeReference<?>>emptyList());
        return executeTransactionAsync(function);
    }

    public Future<Bool> getPermissionRead(Address adr) {
        Function function = new Function("getPermissionRead", 
                Arrays.<Type>asList(adr), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public Future<Utf8String> version() {
        Function function = new Function("version", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public Future<Bool> contains(Address userAddress) {
        Function function = new Function("contains", 
                Arrays.<Type>asList(userAddress), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public Future<TransactionReceipt> setPermissionRead(Address user, Bool perm) {
        Function function = new Function("setPermissionRead", Arrays.<Type>asList(user, perm), Collections.<TypeReference<?>>emptyList());
        return executeTransactionAsync(function);
    }

    public Future<Address> getStringAtIndex(Uint256 index) {
        Function function = new Function("getStringAtIndex", 
                Arrays.<Type>asList(index), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public Future<Utf8String> getString(Address userAddress) {
        Function function = new Function("getString", 
                Arrays.<Type>asList(userAddress), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
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

    public Future<Uint256> getCount() {
        Function function = new Function("getCount", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public Future<Uint256> creationTime() {
        Function function = new Function("creationTime", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public Future<TransactionReceipt> deleteString(Address userAddress) {
        Function function = new Function("deleteString", Arrays.<Type>asList(userAddress), Collections.<TypeReference<?>>emptyList());
        return executeTransactionAsync(function);
    }

    public Future<TransactionReceipt> updateString(Address userAddress, Utf8String userData) {
        Function function = new Function("updateString", Arrays.<Type>asList(userAddress, userData), Collections.<TypeReference<?>>emptyList());
        return executeTransactionAsync(function);
    }

    public static Future<RecordStorage> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, BigInteger initialWeiValue) {
        return deployAsync(RecordStorage.class, web3j, credentials, gasPrice, gasLimit, BINARY, "", initialWeiValue);
    }

    public static Future<RecordStorage> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, BigInteger initialWeiValue) {
        return deployAsync(RecordStorage.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "", initialWeiValue);
    }

    public static RecordStorage load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new RecordStorage(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    public static RecordStorage load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new RecordStorage(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static class LogNewStringEventResponse {
        public Address userAddress;

        public Uint256 index;

        public Utf8String data;
    }

    public static class LogUpdateStringEventResponse {
        public Address userAddress;

        public Uint256 index;

        public Utf8String data;
    }

    public static class LogDeleteStringEventResponse {
        public Address userAddress;

        public Uint256 index;
    }

    public static class NotifyAllEventResponse {
    }

    public static class NotifyEventResponse {
        public Utf8String id;
    }
}
