/**
  Main implementation file, responsible for processing all the games and
  printing the summary at the end.
*/

// Include headers for other components.
#include "summary.h"
#include "input.h"
#include "game.h"

int main()
{
  // Keep reading games to the end of input.
  char alet, blet;
  while ( ( alet = getInitial() ) != '\0' ) {
    blet = getInitial();

    // Process this game.
    decideGame( alet, blet );
  }

  // Report the summary at the end.
  reportSummary();
}
