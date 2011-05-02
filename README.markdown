DropSync
=========

DropSync is a ultra-simple version control system designed for use with [Dropbox](http://www.dropbox.com), although it does not have to be.

DropSync is written in Java and currently is for UNIX systems only.

Installation
------------

Place the _DropSync.jar_ file, along with the _globalsett.dss_ file wherever you like, as long as they share a directory.

You might want to add symlinks and entries in menus etc.

### Global Settings ###

As you might have guessed, these live in the _globalsett.dss_ file. This file should always have at least two lines (order irrelevant):

-	__Dropbox DropSync Folder Location__
	
		#d:/path/to/dropbox/dsync/
		
	This is where all the Dropbox-based DropSync settings and project
    directories	will live. If sharing the DropSync directory with others,
    then make sure their lines point to the same place.

-	__FileSystem DropSync Folder Location__
	
		#f:/path/to/local/dsyncfs/
		
	This is where local DropSync settings will live, note that you can
    store your local projects wherever you wish, but this folder has the
    local settings.

That should be it for the installation.


Usage
-----

### Concepts ###

-   A _project_ in DropSync is a directory (no subdirectories, yet)
    containing text files (eg. .c, .txt, .java).

-   A project can reside either in the Dropbox folder or on the local
    filesystem.

-   _Clones_ are projects that are copies of existing projects. These
    copies are stored wherever the user wants on the local filesystem.
    Clones can be made of clones. A clones _parent_ is the project it was
    copied from.

-   _Master_ projects are those which have no parents. 

-   A _MergeDown_ is when a project's parent is merged down into a given
    child. This destroys any changes the child may have.

-   A _MergeUp_ is when a child is merged up into the parent. This will
    copy any changes up, and merge any conflicts.

-   In the case of conflicts, the merged files (using [diff](http://www.gnu.org/software/diffutils/)) will be
    placed in the parent project's directory, with the '.diff' extension.
    These should be edited as required and renamed over the original.
    After this, a _MergeDown_ is recommended to copy any changes.



### Creating Projects ###

DropSync can create projects by either adding an existing directory, or
creating a new one. By adding an existing directory, it is moved (cut) to
the Dropbox DropSync folder. Creating a new project creates it in the
Dropbox DropSync folder also. Use the input box to give a name to a new
project.

### Adding Files to Projects ###

Click the add files button, select the desired files.

### Cloning Projects ###

Select the project you wish to clone, then click the clone button.
Choose where to save the new project.

### Deleting Projects ###

Select the project you wish to delete. Click the delete button.

### MergeDown Project ###

Select the project you want to merge down to, click the button. This will
overwrite any changes previously made.

### MergeUp Project ###

Select project you want to merge up from, click button. See concepts for
warnings.

Advanced Options
----------------

To turn off warnings, add the following line to your _globalsett.dss_
file:

    #w:1

