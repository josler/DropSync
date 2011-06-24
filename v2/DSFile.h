/*
 * DSFile.h
 *
 *  Created on: 24 Jun 2011
 *      Author: James Osler
 */

#ifndef DSFILE_H_
#define DSFILE_H_

#include <iostream>

using namespace std;

class DSFile {
public:
	DSFile(string name, string directory);
	virtual ~DSFile();
    string getDirectory() const;
    string getExtension() const;
    string getName() const;
    string getFileString() const;
    int getVersion() const;
    void setDirectory(string directory);
    void setName(string name);
    void setVersion(int version);
private:
	string name;
	string directory;
	string extension;
	int version;
};

#endif /* DSFILE_H_ */
