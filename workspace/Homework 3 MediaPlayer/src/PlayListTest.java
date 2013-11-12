import static org.junit.Assert.*;

import org.junit.Test;


public class PlayListTest {

	@Test
	public void addPlayListTest() {
		PlayList pl = new PlayList("Untitled");
		pl.addPlayList(new PlayList("Just Added"));
		assertEquals("Huzzah!", "Just Added", pl.getPlayableList().get(0).getName());
	}
	@Test
	public void addPlayListTest2() {
		PlayList pl = new PlayList("Untitled");
		pl.addPlayList(new PlayList("Just Added"));
		pl.addPlayList(new PlayList("Just Added"));
		pl.addPlayList(new PlayList("different one"));
		assertEquals("Huzzah!", 2, pl.getPlayableList().size());
	}

	@Test
	public void addPlayableTest() {
		PlayList pl = new PlayList("Untitled");
		pl.addPlayable(new Song("artist", "song", "file"));
		pl.addPlayable(new Video("video", "artist", 5, 5, "http://www.youtube.com/embed/FzRH3iTQPrk"));
		assertEquals("Huzzah!", "song", pl.getPlayableList().get(0).getName());
		assertEquals("Huzzah!", "http://www.youtube.com/embed/FzRH3iTQPrk", pl.getPlayableList().get(1).getName());
	}
	
	@Test
	public void loadMediaTest() {
		PlayList pl = new PlayList("Untitled");
		pl.loadMedia("C:\\Users\\Student\\Desktop\\testing2.txt");
		assertEquals("Not Loaded", "http://www.youtube.com/embed/k5mfcTfK9Nc", pl.getPlayableList().get(0).getName());
	}
}
