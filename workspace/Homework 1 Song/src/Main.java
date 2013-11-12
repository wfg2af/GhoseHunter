import java.io.*;
public class Main {
	public static void main(String[] args) throws FileNotFoundException
	{
		PlayList Cubey = new PlayList("Hello");
			Cubey.loadSongs("C:\\Users\\Student\\Desktop\\testing.txt");
		for(int index = 0; index < 3; index++)
		{
			System.out.println(Cubey.getSongList().get(index));
			System.out.println(Cubey.getSongList().get(index).getMinutes());
			System.out.println(Cubey.getSongList().get(index).getSeconds());
		}
		System.out.println(Cubey.totalPlayTime());
	}
}
