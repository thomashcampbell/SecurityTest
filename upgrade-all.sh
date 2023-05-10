#!/bin/sh

# https://rlogiacco.wordpress.com/2012/02/23/maven-pom-version-management/

git pull

# Updates the parent section of the POM 
mvn versions:update-parent -Dincludes=com.aotscc:*

# Updates the properties section of the POM
mvn versions:update-properties -Dincludes=com.aotscc:*

# Updates anything that's a -SNAPSHOT 
mvn versions:use-latest-releases -Dincludes=com.aotscc:*

# Gets rid of pom.xml.versionsBackup
mvn versions:commit -Dincludes=com.aotscc:*
