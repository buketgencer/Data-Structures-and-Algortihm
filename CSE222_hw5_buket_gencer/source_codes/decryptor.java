import java.util.Map;
import java.util.Iterator;

public class decryptor {
	private Map<Character, Map<Character, Character>> map;
	private String key;
	private String keystream = "";
	private String plain_text = "";
	private String cipher_text;
	
	public decryptor(Map<Character, Map<Character, Character>> _map, String _key, String text) {

	}

	public void decrypt() {
		// do not edit this method
		generate_keystream();
		generate_plain_text();
	}
	
	private void generate_keystream() {

	}
	
	private void generate_plain_text() {
		// You must use map.get(x).keySet() with an iterator in this method
	}

	public String get_keystream() {
		
	}
	
	public String get_plain_text() {
		
	}
}
