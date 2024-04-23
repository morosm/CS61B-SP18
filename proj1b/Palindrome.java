public class Palindrome {
    public Deque<Character> wordToDeque(String word){
        Deque<Character> rst = new LinkedListDeque<Character>();
        for(int i = 0; i < word.length(); i++){
            rst.addLast(new Character(word.charAt(i)));
        }
        return rst;
    }

    public boolean isPalindrome(String word){
        var deque = wordToDeque(word);
        while(deque.size() > 1){
            var f = deque.removeFirst();
            var l = deque.removeLast();
            //char是值类型可以使用==
            //string需要用equals
            if(f != l){
                return false;
            }
        }
        return true;
    }

    public boolean isPalindrome(String word, CharacterComparator cc){
        var deque = wordToDeque(word);
        while(deque.size() > 1) {
            var f = deque.removeFirst();
            var l = deque.removeLast();
            if (!cc.equalChars(f, l)) {
                return false;
            }
        }
        return true;
    }
}
