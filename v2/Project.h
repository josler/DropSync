/*
 * Project.h
 *
 *  Created on: 24 Jun 2011
 *      Author: James Osler
 */

#ifndef PROJECT_H_
#define PROJECT_H_

#include <iostream>
#include <list>

#include "DSFile.h"

using namespace std;

class Project {
public:
	Project();
	virtual ~Project();
	string name;
	string directory;
	list<DSFile> files;
private:
	int id;
	int masterid;
	list<Project> childprojects;
};

#endif /* PROJECT_H_ */
