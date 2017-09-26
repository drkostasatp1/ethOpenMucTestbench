pragma solidity ^0.4.16;

import "./Validator.sol";
import "./userPermissions.sol";
import "./mortal.sol";
import "./versionable.sol";

contract RangeChecker is Validator, mortal, userPermissions, versionable{
 
 struct RanngeCheckValues{
    int high;
    int low;
}   

RanngeCheckValues private  rangeCheckValues;
function RangeChecker() public {
    
}
/****************************************************************
* This method will return true if a vlaue is in a
* specified range and false if the value is out of bounds
******************************************************************/
function isValid(int value) requireRead() public constant  requireRead() 
returns (bool inRange) 
{
    if ((value < rangeCheckValues.low) || (value > rangeCheckValues.high))
    {
      // Post notification -> This method
       //outOfBounds(msg.sender,value);
       return false;
      }
      else return true;
}
/*******************************************************************************************
* This method will return a value that is bound by the limits set by the smart contract owner
*******************************************************************************************/
function getBoundedValue (int value) requireRead() public  constant requireRead() 
returns (int retVal) 
{
    if (isValid(value)) return value;
    else
    if (value < rangeCheckValues.low) 
        return rangeCheckValues.low;
    else 
        return rangeCheckValues.high;
}
/*******************************************************************************************
* Getters and Setters follow below
*******************************************************************************************/
 function getHighValue() requireRead()
    public
    constant
    returns(int highValue)
  {
      return rangeCheckValues.high;
  }
 function getLowValue() requireRead()
    public
    constant
    returns(int lowValue)
  {
      return rangeCheckValues.low;
  }
function setRangeValues(int high, int low)  onlyBy(owner) public
{
    rangeCheckValues.high = high;
    rangeCheckValues.low = low;
}   
}