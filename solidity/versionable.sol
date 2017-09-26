pragma solidity ^0.4.16;

contract versionable {

string private version;
string private description;

function getVersion() 
public 
constant
returns (string v)
{
	return version;
}
function setVersion(string v) 
public 
{
	version=v;
}


function getDescription() 
public 
constant
returns (string d)
{
	return description;
}
function setDescription(string d) 
public 
{
	description=d;
}

}