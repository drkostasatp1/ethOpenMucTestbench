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
public final class CRUDStorage extends Contract {
    private static final String BINARY = "606060405260008054600160a060020a03191633600160a060020a03161790554260015534156200002f57600080fd5b5b5b6200004c33600164010000000062000b126200012f82021704565b6200006733600164010000000062000ceb6200017c82021704565b5b620000b460408051908101604052601581527f312e362d7769746820696e6865726974616e6e63650000000000000000000000602082015264010000000062000bc1620001c482021704565b6200012860606040519081016040908152602a82527f53696d706c65204352554420436f6e747261637420776974682075736572207060208301527f65726d697373696f6e73000000000000000000000000000000000000000000009082015264010000000062000d41620001de82021704565b5b620002a2565b600054600160a060020a0390811690331681146200014c57600080fd5b600160a060020a0383166000908152600260205260409020805461ff001916610100841515021790555b5b505050565b600054600160a060020a0390811690331681146200019957600080fd5b600160a060020a0383166000908152600260205260409020805460ff19168315151790555b5b505050565b6003818051620001d9929160200190620001f8565b505b50565b6004818051620001d9929160200190620001f8565b505b50565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f106200023b57805160ff19168380011785556200026b565b828001600101855582156200026b579182015b828111156200026b5782518255916020019190600101906200024e565b5b506200027a9291506200027e565b5090565b6200029f91905b808211156200027a576000815560010162000285565b5090565b90565b61126880620002b26000396000f3006060604052361561011a5763ffffffff7c01000000000000000000000000000000000000000000000000000000006000350416630cb0c7f0811461011f5780630d8e6e2c146101405780631a092541146101cb5780631e9bf0da14610256578063257a11431461026b5780633801ebc61461029e57806343d726d61461030f57806349ea0618146103245780635dbe47e81461035757806373a920da1461038a5780637518e4fe146103b0578063788bc78c146103e25780637d43442514610435578063877c1214146104cc5780638da5cb5b146104f257806390c3f38f14610521578063a6f9dae114610574578063a87d942c14610595578063d8270dce146105ba578063dc8bee92146105df578063f5187dee14610610575b600080fd5b341561012a57600080fd5b61013e600160a060020a0360043516610683565b005b341561014b57600080fd5b610153610708565b60405160208082528190810183818151815260200191508051906020019080838360005b838110156101905780820151818401525b602001610177565b50505050905090810190601f1680156101bd5780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b34156101d657600080fd5b6101536107b1565b60405160208082528190810183818151815260200191508051906020019080838360005b838110156101905780820151818401525b602001610177565b50505050905090810190601f1680156101bd5780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b341561026157600080fd5b61013e61085a565b005b341561027657600080fd5b61028a600160a060020a03600435166108b0565b604051901515815260200160405180910390f35b34156102a957600080fd5b6102fd60048035600160a060020a03169060446024803590810190830135806020601f820181900481020160405190810160405281815292919060208401838380828437509496506108d295505050505050565b60405190815260200160405180910390f35b341561031a57600080fd5b61013e610a4e565b005b341561032f57600080fd5b61028a600160a060020a0360043516610a7a565b604051901515815260200160405180910390f35b341561036257600080fd5b61028a600160a060020a0360043516610aa1565b604051901515815260200160405180910390f35b341561039557600080fd5b61013e600160a060020a03600435166024351515610b12565b005b34156103bb57600080fd5b6103c6600435610b5e565b604051600160a060020a03909116815260200160405180910390f35b34156103ed57600080fd5b61013e60046024813581810190830135806020601f82018190048102016040519081016040528181529291906020840183838082843750949650610bc195505050505050565b005b341561044057600080fd5b610153600160a060020a0360043516610bd9565b60405160208082528190810183818151815260200191508051906020019080838360005b838110156101905780820151818401525b602001610177565b50505050905090810190601f1680156101bd5780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b34156104d757600080fd5b61013e600160a060020a03600435166024351515610ceb565b005b34156104fd57600080fd5b6103c6610d32565b604051600160a060020a03909116815260200160405180910390f35b341561052c57600080fd5b61013e60046024813581810190830135806020601f82018190048102016040519081016040528181529291906020840183838082843750949650610d4195505050505050565b005b341561057f57600080fd5b61013e600160a060020a0360043516610d59565b005b34156105a057600080fd5b6102fd610da3565b60405190815260200160405180910390f35b34156105c557600080fd5b6102fd610dd8565b60405190815260200160405180910390f35b34156105ea57600080fd5b6102fd600160a060020a0360043516610dde565b60405190815260200160405180910390f35b341561061b57600080fd5b61028a60048035600160a060020a03169060446024803590810190830135806020601f82018190048102016040519081016040528181529291906020840183838082843750949650610ff195505050505050565b604051901515815260200160405180910390f35b680ad78ebc5ac6200000348190101561069b57600080fd5b6000805473ffffffffffffffffffffffffffffffffffffffff1916600160a060020a0384161790555b5b803411156107025733600160a060020a03166108fc8234039081150290604051600060405180830381858888f19350505050151561070257600080fd5b5b5b5050565b610710611136565b60038054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156107a65780601f1061077b576101008083540402835291602001916107a6565b820191906000526020600020905b81548152906001019060200180831161078957829003601f168201915b505050505090505b90565b6107b9611136565b60048054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156107a65780601f1061077b576101008083540402835291602001916107a6565b820191906000526020600020905b81548152906001019060200180831161078957829003601f168201915b505050505090505b90565b600054600160a060020a03908116903316811461087657600080fd5b60015462375f0001428190101561088c57600080fd5b6000805473ffffffffffffffffffffffffffffffffffffffff191690555b5b505b50565b600160a060020a03811660009081526002602052604090205460ff165b919050565b33600160a060020a0316600090815260026020526040812054610100900460ff1615156108fe57600080fd5b61090783610aa1565b1561091157600080fd5b600160a060020a038316600090815260056020526040902082805161093a929160200190611148565b5060016006805480600101828161095191906111c7565b916000526020600020900160005b8154600160a060020a038089166101009390930a838102910219909116179091556000818152600560205260409081902093909203600193909301839055917f67a48f38e10dc5aac739d90e6c86181e1dec2cfee3269002e83dbbc0941cb2c99185905182815260406020820181815290820183818151815260200191508051906020019080838360005b83811015610a035780820151818401525b6020016109ea565b50505050905090810190601f168015610a305780820380516001836020036101000a031916815260200191505b50935050505060405180910390a250600654600019015b5b92915050565b60005433600160a060020a03908116911614610a6957600080fd5b600054600160a060020a0316ff5b5b565b600160a060020a038116600090815260026020526040902054610100900460ff165b919050565b6006546000901515610ab5575060006108cd565b600160a060020a038216600081815260056020526040902060010154600680549091908110610ae057fe5b906000526020600020900160005b9054906101000a9004600160a060020a0316600160a060020a03161490505b919050565b600054600160a060020a039081169033168114610b2e57600080fd5b600160a060020a0383166000908152600260205260409020805461ff001916610100841515021790555b5b505050565b33600160a060020a0316600090815260026020526040812054610100900460ff161515610b8a57600080fd5b6006805483908110610b9857fe5b906000526020600020900160005b9054906101000a9004600160a060020a031690505b5b919050565b6003818051610702929160200190611148565b505b50565b610be1611136565b33600160a060020a0316600090815260026020526040902054610100900460ff161515610c0d57600080fd5b610c1682610aa1565b1515610c2157600080fd5b6005600083600160a060020a0316600160a060020a031681526020019081526020016000206000018054600181600116156101000203166002900480601f016020809104026020016040519081016040528092919081815260200182805460018160011615610100020316600290048015610cdd5780601f10610cb257610100808354040283529160200191610cdd565b820191906000526020600020905b815481529060010190602001808311610cc057829003601f168201915b505050505090505b5b919050565b600054600160a060020a039081169033168114610d0757600080fd5b600160a060020a0383166000908152600260205260409020805460ff19168315151790555b5b505050565b600054600160a060020a031681565b6004818051610702929160200190611148565b505b50565b600054600160a060020a039081169033168114610d7557600080fd5b6000805473ffffffffffffffffffffffffffffffffffffffff1916600160a060020a0384161790555b5b5050565b33600160a060020a0316600090815260026020526040812054610100900460ff161515610dcf57600080fd5b506006545b5b90565b60015481565b33600160a060020a031660009081526002602052604081205481908190610100900460ff161515610e0e57600080fd5b610e1784610aa1565b1515610e2257600080fd5b600160a060020a03841660009081526005602052604090206001015460068054919350906000198101908110610e5457fe5b906000526020600020900160005b9054906101000a9004600160a060020a0316905080600683815481101515610e8657fe5b906000526020600020900160005b8154600160a060020a039384166101009290920a9182029184021916179055811660009081526005602052604090206001018290556006805490610edc9060001983016111c7565b5083600160a060020a03167fc081e614396db3b3fb106e28510023a2809ea427581eb82dc77e8c8fe9ead0848360405190815260200160405180910390a2600160a060020a038116600081815260056020526040908190207fa0fe756b7d8c5beeccd8b5643a32e5c78f85181b84521b253c61863dcbbd97e8918591905182815260406020820181815283546002600019610100600184161502019091160491830182905290606083019084908015610fd65780601f10610fab57610100808354040283529160200191610fd6565b820191906000526020600020905b815481529060010190602001808311610fb957829003601f168201915b5050935050505060405180910390a28192505b5b5050919050565b33600160a060020a0316600090815260026020526040812054610100900460ff16151561101d57600080fd5b61102683610aa1565b151561103157600080fd5b600160a060020a038316600090815260056020526040902082805161105a929160200190611148565b50600160a060020a0383166000818152600560205260409081902060018101547fa0fe756b7d8c5beeccd8b5643a32e5c78f85181b84521b253c61863dcbbd97e892909190518281526040602082018181528354600260001961010060018416150201909116049183018290529060608301908490801561111c5780601f106110f15761010080835404028352916020019161111c565b820191906000526020600020905b8154815290600101906020018083116110ff57829003601f168201915b5050935050505060405180910390a25060015b5b92915050565b60206040519081016040526000815290565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f1061118957805160ff19168380011785556111b6565b828001600101855582156111b6579182015b828111156111b657825182559160200191906001019061119b565b5b506111c392915061121b565b5090565b815481835581811511610b5857600083815260209020610b5891810190830161121b565b5b505050565b815481835581811511610b5857600083815260209020610b5891810190830161121b565b5b505050565b6107ae91905b808211156111c35760008155600101611221565b5090565b905600a165627a7a72305820d77206abb34385330400d563711ab2065f83a7ebc94c5d5046bc117da777a5780029";

    private CRUDStorage(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    private CRUDStorage(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
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

    public Future<TransactionReceipt> setString(Address userAddress, Utf8String data) {
        Function function = new Function("setString", Arrays.<Type>asList(userAddress, data), Collections.<TypeReference<?>>emptyList());
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

    public Future<TransactionReceipt> setVersion(Utf8String v) {
        Function function = new Function("setVersion", Arrays.<Type>asList(v), Collections.<TypeReference<?>>emptyList());
        return executeTransactionAsync(function);
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

    public Future<TransactionReceipt> setDescription(Utf8String d) {
        Function function = new Function("setDescription", Arrays.<Type>asList(d), Collections.<TypeReference<?>>emptyList());
        return executeTransactionAsync(function);
    }

    public Future<TransactionReceipt> changeOwner(Address _newOwner) {
        Function function = new Function("changeOwner", Arrays.<Type>asList(_newOwner), Collections.<TypeReference<?>>emptyList());
        return executeTransactionAsync(function);
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

    public static Future<CRUDStorage> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, BigInteger initialWeiValue) {
        return deployAsync(CRUDStorage.class, web3j, credentials, gasPrice, gasLimit, BINARY, "", initialWeiValue);
    }

    public static Future<CRUDStorage> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, BigInteger initialWeiValue) {
        return deployAsync(CRUDStorage.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "", initialWeiValue);
    }

    public static CRUDStorage load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new CRUDStorage(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    public static CRUDStorage load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new CRUDStorage(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
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
