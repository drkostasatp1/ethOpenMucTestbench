# ethOpenMucTestbench
## Requirements

- A Java Runtime Environment and SDK.

- A Blockchain node running locally with rpc over tcp enabled. This could be the Ethereum Wallet, the Mist Ethereum Browser or the commandline version of geth. 

See the [guide section](guide/README.md) for setp by step instructions (performed on a windows machine, but for linux and mac os the required steps are similar)

## Compiling and Executing the code

Commands for a Linux/Osx Shell

Checkout the repository using git:

	git clone https://github.com/mposch/ethOpenMucTestbench.git

Download dependencies, compile and Run the Code using gradle:

	cd ethOpenMucTestbench &&
	./gradlew bootRun

