/** 
  Implementation file for the game component, responsible for
  determining the winner of a rock-paper-scissors game.
*/

// ...

#include <stdlib.h>
#include <stdio.h>
#include "game.h"
#include "summary.h"

/** 
 Called when the program gets a word starting with an invalid letter.
 It prints an error message and exits the program unsuccessfully.
 
 This funciton is static, so it's not visible to other components.
 It's for internal use only by this component, and it shouldn't not
 get a prototype in the header.
 */
static void invalid()
{
  printf( "Invalid character\n" );
  exit( EXIT_FAILURE );
}

// This function is for use by other components.  It should get a prototype
// in the header.  That's where it's javadoc comment would normally go.
void decideGame( char alet, char blet )
{
    // A error checking
    if ( alet != 'r' && alet != 'p' && alet != 's' ) {
        invalid();
    }

    // B error checking
    if ( blet != 'r' && blet != 'p' && blet != 's' ) {
        invalid();
    }

    // Ties
    if( alet == 's' && blet == 's' ) {
        printf( "Players tie\n" );
        TieTotal++;
    }
    if( alet == 'p' && blet == 'p' ) {
        printf( "Players tie\n" );
        TieTotal++;
    }
    if( alet == 'r' && blet == 'r' ) {
        printf( "Players tie\n" );
        TieTotal++;
    }

    // A wins
    if( alet == 's' && blet == 'p' ) {
        printf( "Player A wins\n" );
        AWinTotal++;
    }
    if( alet == 'r' && blet == 's' ) {
        printf( "Player A wins\n" );
        AWinTotal++;
    }
    if( alet == 'p' && blet == 'r' ) {
        printf( "Player A wins\n" );
        AWinTotal++;
    }

    // B wins
    if( alet == 'r' && blet == 'p' ) {
        printf( "Player B wins\n" );
        BWinTotal++;
    }
    if( alet == 's' && blet == 'r' ) {
        printf( "Player B wins\n" );
        BWinTotal++;
    }
    if( alet == 'p' && blet == 's' ) {
        printf( "Player B wins\n" );
        BWinTotal++;
    }
}
