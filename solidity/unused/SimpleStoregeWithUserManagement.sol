pragma solidity ^0.4.11;
/**
 * This is the simplest form of a storage contract in ethereum. It has no security measures in place, and everyone can read and write
 */
 contract simpleStorageWithUsers {
address public owner; 
/**
 * This is the contract Constructor that is executed once the contract is created
 */
 function dataMapping()
 {
     // Thiswill store the creator of the contract to enablle user persmissions management
     owner = msg.sender;
     // Set the permission for the owner
     setPermissionWrite(owner);
     setPermissionRead(owner);
 }
// uint is a shortcut for uint256, meaning that a uint can hold up to 256 bits
// this will define an hashtable to store unlimited amounts of key>value paris 
struct userPermissions{
    bool write;
    bool read;
}
mapping(address => string) private dataStorage;
mapping(address => userPermissions) public theUserPermissions;

// Everyone can access the strings, if the address is known
/**
 * getters and setter method follow
*/
function getString (address addr) returns (string s)
{
    // Require the that sender has read privileges
    require (theUserPermissions[msg.sender].read);
    return dataStorage[addr];
}
function setString(address addr, string newValue) {
    require (theUserPermissions[msg.sender].write);
        dataStorage[addr] = newValue;
    }
/**
 * Following are functions to set and reset the permissions of users. 
 */
function setPermissionRead(address user) 
{
    // Only the owner can set the permissions of this contract
    require (msg.sender == owner);
    theUserPermissions[user].read = true;
}
function clearPermissionRead(address user) 
{
    // Only the owner can set the permissions of this contract
    require (msg.sender == owner);
    theUserPermissions[user].read = false;
}
function setPermissionWrite(address user) 
{
    // Only the owner can set the permissions of this contract
    require (msg.sender == owner);
    theUserPermissions[user].write = true;
}
function clearPermissionWrite(address user) 
{
    // Only the owner can set the permissions of this contract
    require (msg.sender == owner);
    theUserPermissions[user].write = false;
}

}// end contract