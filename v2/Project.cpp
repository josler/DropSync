/*
 * Project.cpp
 *
 *  Created on: 24 Jun 2011
 *      Author: James Osler
 */

#include "Project.h"

using namespace std;

Project::Project() {
	this->masterid = 0;
	this->files = new list<DSFile>;
	this->childprojects = new list<DSFile>;
}

Project::~Project() {
	// TODO Auto-generated destructor stub
}
