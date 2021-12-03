// ISongInterface.aidl
package com.itis.templateitis;

// Declare any non-default types here with import statements

interface ISongInterface {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void basicTypes(int anInt, String aString);

    int sum(int a, int b);

    void pause();

    void play();

    Song getSong();

    int processId();
}

parcelable Song;
