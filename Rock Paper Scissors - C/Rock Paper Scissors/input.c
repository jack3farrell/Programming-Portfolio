/**
  Implementation file for the input component, responsible for reading the
  shape thrown by a player.
*/

// ...

#include <stdio.h>
#include <ctype.h>
#include "input.h"

// This function is for use by other components.  It should get a prototype
// in the header.  That's where it's javadoc comment would normally go.
char getInitial()
{
int ch;
// Skip leading spaces and newlines
do {
    ch = getchar();
    if ( ch == EOF ) {
        return 0;
    }
} while ( ch == ' ' || ch == '\n' );

// Store the first non-space character
char init = ch;

// Read the rest of the word
while (( ch = getchar()) != EOF && ch != ' ' && ch != '\n' ) {
    // This does nothing
}

return init;
}   