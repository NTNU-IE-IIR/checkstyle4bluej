# Update bluej extension library

1.  Get the latest bluej version and install it
2.  Create a folder `lib` in your project. Move to this folder (`cd lib`).
3.  Install the library `bluejext2.jar` into this local repository (the `lib`-folder):

        PATH_TO_BLUEJ_INSTALL_DIR=${HOME}/bluej
        VERSION=5.0.0
        mvn install:install-file -Dfile=${PATH_TO_BLUEJ_INSTALL_DIR}/lib/bluejext2.jar \
                                 -DgroupId=bluej \
                                 -DartifactId=bluejext2 \
                                 -Dversion=${VERSION} \
                                 -Dpackaging=jar \
                                 -DlocalRepositoryPath=.

On MacOSX the `bluejext2.jar`-file is located at `/Applications/BlueJ 5.0.1/BlueJ.app/Contents/Resources/Java`