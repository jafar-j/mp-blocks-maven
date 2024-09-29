package edu.grinnell.csc207;

import static org.junit.jupiter.api.Assertions.assertEquals;

import edu.grinnell.csc207.blocks.Alphabetical;
import org.junit.jupiter.api.Test;

/** Tests of our alphabetical block. */
public class TestOurBlock {
  /** Does the alphabetical class successfully build alphabetical rectangles? */
  @Test
  public void testAlphabetical() throws Exception {
    Alphabetical alpha = new Alphabetical(6, 2);
    assertEquals(6, alpha.width(), "Basic alphabetical rectangle has correct width.");
    assertEquals(2, alpha.height(), "Basic alphabetical rectangle has correct height.");
    assertEquals("ABCDEF", alpha.row(0), "Basic alphabetical rectangle has correct components.");
  } // testAlphabetical()

  /** Testing edge cases for alphabetical class. */
  @Test
  public void alphabeticalEdgeCases() throws Exception {
    Alphabetical oneChar = new Alphabetical(1, 1);
    Alphabetical wideRect = new Alphabetical(100, 1);
    Alphabetical tallRect = new Alphabetical(1, 100);
    assertEquals(1, oneChar.width(), "One character block width.");
    assertEquals(1, oneChar.height(), "One character block height.");
    assertEquals("A", oneChar.row(0), "One character block is correct letter.");
    assertEquals('V', wideRect.row(0).charAt(99), "Wide rectangle correct letter calculation.");
    assertEquals('V', tallRect.row(99).charAt(0), "Tall rectangle correct letter calculation.");
  } // alphabeticalEdgeCases()
} // end TestOutBlock
