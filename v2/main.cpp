/*
 * main.cpp
 *
 *  Created on: 24 Jun 2011
 *      Author: James Osler
 */

#include <iostream>

#include "DSFile.h"

using namespace std;

int main()
{
	DSFile ds("noext", "/home/jamie/moo/");
	DSFile ds2("test.extension", "/home/jamie/moo/");
	DSFile ds3("test.with.ext", "/home/jamie/moo/");
	DSFile ds4(".hidden", "/home/jamie/moo/");
	DSFile ds5(".hidden.ext", "/home/jamie/moo/");

	cout << ds.getFileString() << endl;
	cout << ds2.getFileString() << endl;
	cout << ds3.getFileString() << endl;
	cout << ds4.getFileString() << endl;
	cout << ds5.getFileString() << endl;

	return 0;
}



