class Solution {
    public boolean isAnagram(String s, String t) {
        HashMap<Character,Integer> sMap = new HashMap<>();
        for(Character a : s.toCharArray()){
            sMap.put(a,sMap.getOrDefault(a,0) + 1);
        }
        for(Character b : t.toCharArray()){
            sMap.put(b,sMap.getOrDefault(b, 0) - 1);
        }
        for(Character c : sMap.keySet()){
            if(sMap.get(c) != 0){
                return false;
            }
        }
        return true;
    }
}