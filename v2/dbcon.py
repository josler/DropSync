#!/usr/bin/env python

import sqlite3
from project import *
import os

class dbcon():
	"""
	Database management
	""" 
	
	def connect(self):
		self.conn = sqlite3.connect("dbsync.db")
		self.curs = self.conn.cursor()
		self.curs.execute('PRAGMA foreign_keys = on') # turn on foreign keys!
	
	def create(self):
		self.curs.execute('''CREATE TABLE project (
											projectid INTEGER PRIMARY KEY,
											name TEXT,
											masterid INTEGER, 
											directory TEXT)''')
		self.curs.execute('''CREATE TABLE file (
											fileid INTEGER PRIMARY KEY,
											projectid INTEGER,
											name TEXT,
											version INTEGER,
											FOREIGN KEY(projectid) REFERENCES project(projectid))''')
							
									
		self.conn.commit()
	
	def insertProject(self,project):
		self.curs.execute('''INSERT INTO project
						(name, masterid, directory) VALUES
						(?,?,?)''',
						(project.name, project.masterid, project.directory))
		addedID = self.curs.lastrowid # ID of project just inserted
		for name, dfile in project.files.items():
			self.curs.execute('''INSERT INTO file
						(projectid, name, version) VALUES
						(?,?,?)''',
						(addedID, dfile.getFileString(), dfile.version))
		self.conn.commit()
		
	def readProjects(self):
		projlist = []
		print "\n"
		self.curs.execute('''SELECT * FROM project''')
		table = self.curs.fetchall()
		for row in table:
			p = project()
			p.id = row[0]
			p.name = row[1]
			p.directory = row[3]
			p.masterid = row[2]
			self.curs.execute('''SELECT * FROM file WHERE projectid=%d'''%(row[0]))
			for f in self.curs:
				p.addFile(f[2], f[3])
			projlist.append(p)
		return projlist

	def printProjects(self):
		print "\nPROJECTS:\nprojectid, name, masterid, directory"				
		self.curs.execute('''SELECT * FROM project''')
		for row in self.curs:
			print row
			
	def printFiles(self):
		print "\nFILES:\nfileid, projectid, name, version"
		self.curs.execute('''SELECT * FROM file''')
		for row in self.curs:
			print row

if __name__ == "__main__":
	#os.system("rm dbsync.db") # remove old database for testing.
	
	c = dbcon() # start up database
	c.connect()
	#c.create() # create our database tables
	
	# Testing adding projects to database
	#===========================================================================
	# p = project() # setup project
	# p.name = "mao"
	# p.directory = "noop/na"
	# p.masterid = 13
	# p.addFile("woopya")
	# p.addFile("weep")
	# 
	# q = project() # setup another project
	# q.name = "project2"
	# q.directory = "directory/to/it"
	# q.masterid = 2
	# q.addFile("boring.file")	
	# 
	# c.insertProject(p) # insert into database
	# c.insertProject(q)
	# c.printProjects() # query database
	# c.printFiles()
	#===========================================================================
	
	# Reading projects from database
	projlist = c.readProjects()
	for item in projlist:
		item.showProject()
