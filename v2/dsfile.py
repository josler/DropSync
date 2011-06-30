#!/usr/bin/env python

class dsfile():
	"""
	dropsync file
	"""
	
	def __init__(self,name,directory):
		self.setName(name)
		self.directory = directory
		self.version = 1
		
	def getFileString(self):
		if self.extension != "":
			return self.name + "." + self.extension
		else:
			return self.name
		
	def setName(self, name):
		last = name.find(".")
		if(last == -1): # not present
			self.name = name
			self.extension = ""
		else:
			last = name.rfind(".")
			if(last != 0): # normal and hidden with ext
				self.extension = name[last+1:]
				self.name = name[:len(name)-(len(self.extension) + 1)]
			else: # hidden files with no ext
				self.extension = ""
				self.name = name

if __name__ == "__main__":
	da = dsfile("moo1","/ome/jamie/")
	db = dsfile("moo2.ext","/home/jamie/")
	dc = dsfile("moo2.midl.ext","/home/jamie/")
	dd = dsfile(".moo2","/home/jamie/")
	de = dsfile(".moo2.ext","/home/jamie/")

	print da.getFileString()
	print db.getFileString()
	print dc.getFileString()
	print dd.getFileString()
	print de.getFileString()


	
