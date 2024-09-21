
class Node {

    public final char value;
    public boolean isPresent = false;
    public Node[] children = new Node[26];

    public Node (char value) {
        this.value = value;
    }

    public String toString() {
        return value+" "+isPresent+": \n"+Arrays.toString(children);
    }

    public char getCharacter() {
        return value;
    }
}

class Trie {

    // 트리 구조로 만들어야할 것 같은데, 
    // public Node[] nodes = new Node[26];

    public Node start = new Node('\0');
    // Node가 존재한다는 거를, 문자가 존재한다.

    // 시작할 때 nodes[a] a가 null이다, 그럼 그 문자는 없는걸로 구현하겟습니다.

    private int charToIndex(char c) {
        return c - 'a';
    }

    public Trie() {
        
    }

    // word의 넘겨줘서, 재귀를 통해, 각 문자를 한 번씩 타고 내려가면서 있는지 여부를 확인할건데, 없으면 새로 만들고, 없으면 pass 그냥 무시 


    // 만약 abc였다, 
    public void insert(String word) {
        // char nowChar = word.charAt(0);

        // int charIndex = charToIndex(nowChar);

        // if(nodes[charIndex] == null) {// a들어갔을거고
        //     nodes[charIndex] = new Node(nowChar);
        // }

        // // a, children가지고 있을테니
        insert(start, word,0);   
    }
    // b부터 넣길 시작하도록
    private void insert(Node node, String word, int index) {
        if(word.length() <= index) {// 현재가 마지막이겠죠?
            node.isPresent = true;
            return;
        }

        char nowChar = word.charAt(index);
        int charIndex = charToIndex(nowChar);

        if(node.children[charIndex] == null) {
            node.children[charIndex] = new Node(nowChar);
        }

        insert(node.children[charIndex], word, index+1);   
    }
    
    public boolean startsWith(String prefix) {
        return startsWith(start, prefix, 0);
    }

    private boolean startsWith(Node node, String prefix, int index) {
        if(prefix.length() <= index) {
            return true;
        }

        char nowChar = prefix.charAt(index);
        int charIndex = charToIndex(nowChar);

        if(node.children[charIndex] == null) {
            return false;
        }

        return startsWith(node.children[charIndex], prefix, index+1);   
    }
    

    public boolean search(String targetWord) {
        return search(start, targetWord, 0);
    }

    private boolean search(Node node, String targetWord, int index) {
        if(targetWord.length() <= index) {
        
            return node.isPresent;
        }

        char nowChar = targetWord.charAt(index);
        int charIndex = charToIndex(nowChar);

        if(node.children[charIndex] == null) {
            return false;
        }
        return search(node.children[charIndex], targetWord, index+1);   
    }
}