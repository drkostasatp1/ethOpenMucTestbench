pragma solidity ^0.4.16;
import "./ownable.sol";

contract userPermissions is ownable{

struct userPermissionsT{
    bool write;
    bool read;
}
/***********************
 * Constructor
 * *********************/
function userPermissions() public 
{
    setPermissionRead(msg.sender, true);
    setPermissionWrite(msg.sender, true);
}
 // Store the user permissions:
    mapping(address => userPermissionsT) private theUserPermissions;
  
  /*******************************************************************************************
* Function Modifyers. These wrap Functions and execute them based on Conditions. 
 *******************************************************************************************/
 modifier onlyBy(address _account)
    {
        require(msg.sender == _account);
        // Do not forget the "_;"! It will
        // be replaced by the actual function
        // body when the modifier is used.
        _;
    }
    modifier requireRead()
  {
   require (theUserPermissions[msg.sender].read);
    _;
  }
  modifier requireWrite()
  {
   require (theUserPermissions[msg.sender].read);
    _;
  }
  
 /*******************************************************************************************
 * Following are functions to set and reset the permissions of users. 
 *******************************************************************************************/
function getPermissionRead(address adr) 
public 
constant
returns (bool permission)
{
	return theUserPermissions[adr].read;
}
function getPermissionWrite(address adr) 
public 
constant
returns (bool permission)
{
	return theUserPermissions[adr].write;
}
function setPermissionRead(address user, bool perm)  onlyBy(owner) public
{
    theUserPermissions[user].read = perm;
}
function setPermissionWrite(address user, bool perm)  onlyBy(owner) public
{
    theUserPermissions[user].write = perm;
}
}