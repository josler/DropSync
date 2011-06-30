#!/usr/bin/env python
from dsfile import *

class project():
	"""
	a dropsync project
	"""
	def __init__(self, another=None, id=0):
		self.masterid = 0
		self.files = {}
		self.childprojects = {}
		self.id = id
		if another != None:
			self.name = another.name
			self.directory = another.directory
			self.id = another.id
			self.masterid = another.masterid
			self.files = another.files
			self.childprojects = another.childprojects
	
	def addFile(self, filename, version=1):
		dfile = dsfile(filename,self.directory)
		dfile.version = version
		self.files[dfile.name] = dfile 
	
	# WARNING!
	# Bad implementation. When deleting a file we only have the filename.
	def deleteFile(self, dsfile):
		del self.files[dsfile.name]
	
	def updateFiles(self):
		'''update the directories for files'''
		for id, dfile in self.files.items():
			dfile.directory = self.directory
	
	def addChild(self, project):
		self.childprojects[project.id] = project
		
	def deleteChild(self, project):
		del self.childprojects[project.id]

	def showProject(self):
		"""display the project"""
		print "\n"
		print '''%s (%d) : ''' % (self.name, self.id)
		print "Files:"
		for name, dfile in self.files.items():
			print '''%s/%s ''' % (dfile.directory, dfile.getFileString())
		print "Child Projects:"
		for id, cp in self.childprojects.items():
			print '''%s (%d)''' % (cp.directory, id)


if __name__ == "__main__":
	p = project()
	p.name = "namehere"
	p.directory = "/here/i/am"

	p.addFile("file1")
	p.addFile("file2")
	p.addFile("file3")
	
	p.showProject()

