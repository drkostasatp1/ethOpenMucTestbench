// Define the minimal compiler version required
pragma solidity ^0.4.0;
/**
 * This is the simplest form of a storage contract in Ethereum. It has no security measures in place, and everyone can read and write
 */
contract simpleStorage {
// uint is a shortcut for uint256, meaning that a uint can hold up to 256 bits
// this will define an hashtable to store unlimited amounts of key>value paris 
    mapping(address => string) dataStorage;
 // Everyone can access the strings stored in the hashtable, if the address is known
/**
 * getters and setter method follow
*/
function  getString (address addr) constant returns (string s)
{
    return dataStorage[addr];
}

function setString(address addr, string newValue) 
{
     dataStorage[addr] = newValue;
	
}

    
}