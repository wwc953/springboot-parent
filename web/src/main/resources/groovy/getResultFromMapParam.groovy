def addHello(Map map) {
    List<String> list = (List<String>) map.get("list");
    def result = 0;
    for (int i = 0; i < list.size(); i++) {
        result += Integer.parseInt(list.get(i));
    }
    map.put("result", result);
}

addHello(params)