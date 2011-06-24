/*
 * DSFile.cpp
 *
 *  Created on: 24 Jun 2011
 *      Author: James Osler
 */

#include "DSFile.h"

DSFile::DSFile(string name, string directory)
{
	setName(name);
	setDirectory(directory);
	version = 1;

}

DSFile::~DSFile()
{
}

string DSFile::getDirectory() const
{
    return directory;
}

string DSFile::getExtension() const
{
    return extension;
}

string DSFile::getName() const
{
    return name;
}

string DSFile::getFileString() const
{
	if(getExtension() != "")
		return getName() + "." + getExtension();
	else
		return getName();
}

int DSFile::getVersion() const
{
    return version;
}

void DSFile::setDirectory(string directory)
{
    this->directory = directory;
}

void DSFile::setName(string name)
{
	if(name.find(".") == string::npos) // no '.' in name
	{
		this->name = name;
		this->extension = "";
	}
	else
	{
		size_t last = name.rfind("."); // POW!!!
		this->extension = name.substr(last+1);
		this->name = name.substr(0,name.size()-(this->extension.size() + 1));
	}
}

void DSFile::setVersion(int version)
{
    this->version = version;
}



