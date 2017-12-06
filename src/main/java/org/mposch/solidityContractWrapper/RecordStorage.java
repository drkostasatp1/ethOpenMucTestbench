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
import org.web3j.abi.datatypes.generated.Int256;
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
    private static final String BINARY = "606060405260408051908101604052600381527f312e350000000000000000000000000000000000000000000000000000000000602082015260009080516200004d9291602001906200016d565b5060018054600160a060020a03191633600160a060020a031617905542600281905560055534156200007e57600080fd5b5b60018054620000a891600160a060020a039091169064010000000062000b65620000d882021704565b60018054620000d191600160a060020a03909116906401000000006200096f6200012082021704565b5b62000217565b600154600160a060020a039081169033168114620000f557600080fd5b600160a060020a0383166000908152600760205260409020805460ff19168315151790555b5b505050565b600154600160a060020a0390811690331681146200013d57600080fd5b600160a060020a0383166000908152600760205260409020805461ff001916610100841515021790555b5b505050565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f10620001b057805160ff1916838001178555620001e0565b82800160010185558215620001e0579182015b82811115620001e0578251825591602001919060010190620001c3565b5b50620001ef929150620001f3565b5090565b6200021491905b80821115620001ef5760008155600101620001fa565b5090565b90565b61111280620002276000396000f3006060604052361561010f5763ffffffff7c01000000000000000000000000000000000000000000000000000000006000350416630b775355811461011457806317793aa31461013e578063257a1143146101635780633801ebc6146101965780634010d32a1461020757806349ea06181461022257806354fd4d50146102555780635c52c074146102e05780635dbe47e81461030557806373a920da146103385780637518e4fe1461035e5780637d4344251461039057806384486d9014610427578063877c12141461044c5780638da5cb5b14610472578063a87d942c146104a1578063c82d4c19146104c6578063d8270dce146104ee578063dc8bee9214610513578063f5187dee14610544575b600080fd5b341561011f57600080fd5b61012a6004356105b7565b604051901515815260200160405180910390f35b341561014957600080fd5b610151610639565b60405190815260200160405180910390f35b341561016e57600080fd5b61012a600160a060020a036004351661063f565b604051901515815260200160405180910390f35b34156101a157600080fd5b61015160048035600160a060020a03169060446024803590810190830135806020601f8201819004810201604051908101604052818152929190602084018383808284375094965061066195505050505050565b60405190815260200160405180910390f35b341561021257600080fd5b6102206004356024356107d7565b005b341561022d57600080fd5b61012a600160a060020a0360043516610804565b604051901515815260200160405180910390f35b341561026057600080fd5b61026861082b565b60405160208082528190810183818151815260200191508051906020019080838360005b838110156102a55780820151818401525b60200161028c565b50505050905090810190601f1680156102d25780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b34156102eb57600080fd5b6101516108c9565b60405190815260200160405180910390f35b341561031057600080fd5b61012a600160a060020a03600435166108fe565b604051901515815260200160405180910390f35b341561034357600080fd5b610220600160a060020a0360043516602435151561096f565b005b341561036957600080fd5b6103746004356109bb565b604051600160a060020a03909116815260200160405180910390f35b341561039b57600080fd5b610268600160a060020a0360043516610a1e565b60405160208082528190810183818151815260200191508051906020019080838360005b838110156102a55780820151818401525b60200161028c565b50505050905090810190601f1680156102d25780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b341561043257600080fd5b610151610b30565b60405190815260200160405180910390f35b341561045757600080fd5b610220600160a060020a03600435166024351515610b65565b005b341561047d57600080fd5b610374610bac565b604051600160a060020a03909116815260200160405180910390f35b34156104ac57600080fd5b610151610bbb565b60405190815260200160405180910390f35b34156104d157600080fd5b610151600435610bf0565b60405190815260200160405180910390f35b34156104f957600080fd5b610151610c82565b60405190815260200160405180910390f35b341561051e57600080fd5b610151600160a060020a0360043516610c88565b60405190815260200160405180910390f35b341561054f57600080fd5b61012a60048035600160a060020a03169060446024803590810190830135806020601f82018190048102016040519081016040528181529291906020840183838082843750949650610e9b95505050505050565b604051901515815260200160405180910390f35b33600160a060020a0316600090815260076020526040812054610100900460ff1615156105e357600080fd5b33600160a060020a0316600090815260076020526040902054610100900460ff16151561060f57600080fd5b600454821280610620575060035482135b1561062d57506000610631565b5060015b5b5b5b919050565b60055481565b600160a060020a03811660009081526007602052604090205460ff165b919050565b600160a060020a03331660009081526007602052604081205460ff16151561068857600080fd5b610691836108fe565b1561069b57600080fd5b600160a060020a03831660009081526006602052604090208280516106c4929160200190610fe0565b506001600880548060010182816106db919061105f565b916000526020600020900160005b8154600160a060020a038089166101009390930a838102910219909116179091556000818152600660205260409081902093909203600193909301839055917f67a48f38e10dc5aac739d90e6c86181e1dec2cfee3269002e83dbbc0941cb2c99185905182815260406020820181815290820183818151815260200191508051906020019080838360005b8381101561078d5780820151818401525b602001610774565b50505050905090810190601f1680156107ba5780820380516001836020036101000a031916815260200191505b50935050505060405180910390a250600854600019015b92915050565b600154600160a060020a0390811690331681146107f357600080fd5b600383905560048290555b5b505050565b600160a060020a038116600090815260076020526040902054610100900460ff165b919050565b60008054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156108c15780601f10610896576101008083540402835291602001916108c1565b820191906000526020600020905b8154815290600101906020018083116108a457829003601f168201915b505050505081565b33600160a060020a0316600090815260076020526040812054610100900460ff1615156108f557600080fd5b506003545b5b90565b600854600090151561091257506000610631565b600160a060020a03821660008181526006602052604090206001015460088054909190811061093d57fe5b906000526020600020900160005b9054906101000a9004600160a060020a0316600160a060020a03161490505b919050565b600154600160a060020a03908116903316811461098b57600080fd5b600160a060020a0383166000908152600760205260409020805461ff001916610100841515021790555b5b505050565b33600160a060020a0316600090815260076020526040812054610100900460ff1615156109e757600080fd5b60088054839081106109f557fe5b906000526020600020900160005b9054906101000a9004600160a060020a031690505b5b919050565b610a26611089565b33600160a060020a0316600090815260076020526040902054610100900460ff161515610a5257600080fd5b610a5b826108fe565b1515610a6657600080fd5b6006600083600160a060020a0316600160a060020a031681526020019081526020016000206000018054600181600116156101000203166002900480601f016020809104026020016040519081016040528092919081815260200182805460018160011615610100020316600290048015610b225780601f10610af757610100808354040283529160200191610b22565b820191906000526020600020905b815481529060010190602001808311610b0557829003601f168201915b505050505090505b5b919050565b33600160a060020a0316600090815260076020526040812054610100900460ff161515610b5c57600080fd5b506004545b5b90565b600154600160a060020a039081169033168114610b8157600080fd5b600160a060020a0383166000908152600760205260409020805460ff19168315151790555b5b505050565b600154600160a060020a031681565b33600160a060020a0316600090815260076020526040812054610100900460ff161515610be757600080fd5b506008545b5b90565b33600160a060020a0316600090815260076020526040812054610100900460ff161515610c1c57600080fd5b33600160a060020a0316600090815260076020526040902054610100900460ff161515610c4857600080fd5b610c51826105b7565b15610c5d575080610631565b600454821215610c705750600454610631565b50600354610631565b5b5b5b5b919050565b60025481565b33600160a060020a031660009081526007602052604081205481908190610100900460ff161515610cb857600080fd5b610cc1846108fe565b1515610ccc57600080fd5b600160a060020a03841660009081526006602052604090206001015460088054919350906000198101908110610cfe57fe5b906000526020600020900160005b9054906101000a9004600160a060020a0316905080600883815481101515610d3057fe5b906000526020600020900160005b8154600160a060020a039384166101009290920a9182029184021916179055811660009081526006602052604090206001018290556008805490610d8690600019830161105f565b5083600160a060020a03167fc081e614396db3b3fb106e28510023a2809ea427581eb82dc77e8c8fe9ead0848360405190815260200160405180910390a2600160a060020a038116600081815260066020526040908190207fa0fe756b7d8c5beeccd8b5643a32e5c78f85181b84521b253c61863dcbbd97e8918591905182815260406020820181815283546002600019610100600184161502019091160491830182905290606083019084908015610e805780601f10610e5557610100808354040283529160200191610e80565b820191906000526020600020905b815481529060010190602001808311610e6357829003601f168201915b5050935050505060405180910390a28192505b5b5050919050565b33600160a060020a0316600090815260076020526040812054610100900460ff161515610ec757600080fd5b610ed0836108fe565b1515610edb57600080fd5b600160a060020a0383166000908152600660205260409020828051610f04929160200190610fe0565b50600160a060020a0383166000818152600660205260409081902060018101547fa0fe756b7d8c5beeccd8b5643a32e5c78f85181b84521b253c61863dcbbd97e8929091905182815260406020820181815283546002600019610100600184161502019091160491830182905290606083019084908015610fc65780601f10610f9b57610100808354040283529160200191610fc6565b820191906000526020600020905b815481529060010190602001808311610fa957829003601f168201915b5050935050505060405180910390a25060015b5b92915050565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f1061102157805160ff191683800117855561104e565b8280016001018555821561104e579182015b8281111561104e578251825591602001919060010190611033565b5b5061105b9291506110c5565b5090565b8154818355818115116107fe576000838152602090206107fe9181019083016110c5565b5b505050565b60206040519081016040526000815290565b8154818355818115116107fe576000838152602090206107fe9181019083016110c5565b5b505050565b6108fa91905b8082111561105b57600081556001016110cb565b5090565b905600a165627a7a72305820546daa8ddc843cc4e16388a3643995de7e3eb6af3f12982bcdb3f5e5a1cc7c150029";

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

    public List<OutOfBoundsEventResponse> getOutOfBoundsEvents(TransactionReceipt transactionReceipt) {
        final Event event = new Event("outOfBounds", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Int256>() {}));
        List<EventValues> valueList = extractEventParameters(event, transactionReceipt);
        ArrayList<OutOfBoundsEventResponse> responses = new ArrayList<OutOfBoundsEventResponse>(valueList.size());
        for (EventValues eventValues : valueList) {
            OutOfBoundsEventResponse typedResponse = new OutOfBoundsEventResponse();
            typedResponse.userAddress = (Address) eventValues.getNonIndexedValues().get(0);
            typedResponse.value = (Int256) eventValues.getNonIndexedValues().get(1);
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<OutOfBoundsEventResponse> outOfBoundsEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        final Event event = new Event("outOfBounds", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Int256>() {}));
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(event));
        return web3j.ethLogObservable(filter).map(new Func1<Log, OutOfBoundsEventResponse>() {
            @Override
            public OutOfBoundsEventResponse call(Log log) {
                EventValues eventValues = extractEventParameters(event, log);
                OutOfBoundsEventResponse typedResponse = new OutOfBoundsEventResponse();
                typedResponse.userAddress = (Address) eventValues.getNonIndexedValues().get(0);
                typedResponse.value = (Int256) eventValues.getNonIndexedValues().get(1);
                return typedResponse;
            }
        });
    }

    public Future<Bool> isInRange(Int256 value) {
        Function function = new Function("isInRange", 
                Arrays.<Type>asList(value), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public Future<Uint256> lastCalled() {
        Function function = new Function("lastCalled", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeCallSingleValueReturnAsync(function);
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

    public Future<TransactionReceipt> setRangeValues(Int256 high, Int256 low) {
        Function function = new Function("setRangeValues", Arrays.<Type>asList(high, low), Collections.<TypeReference<?>>emptyList());
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

    public Future<Int256> getHighValue() {
        Function function = new Function("getHighValue", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Int256>() {}));
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

    public Future<Uint256> getCount() {
        Function function = new Function("getCount", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeCallSingleValueReturnAsync(function);
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

    public static class OutOfBoundsEventResponse {
        public Address userAddress;

        public Int256 value;
    }
}
