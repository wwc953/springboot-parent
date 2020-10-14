def sayhello(List<String> list) {
    def result = 0;
    for (int i = 0; i < list.size(); i++) {
        result += Integer.parseInt(list.get(i));
    }
    return result;
}

sayhello(params)
