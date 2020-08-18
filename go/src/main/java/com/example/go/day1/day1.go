package main

import "fmt"

var a int = 13

func main() {
	//a := 12
	fmt.Println(a)

	// 声明一个变量并初始化
	var a1 = "RUNOOB"
	fmt.Println(a1)

	// 默认为0
	var b int
	fmt.Println(b)

	// bool 默认值为 false
	var c bool
	fmt.Println(c)

	//默认""
	var str string
	fmt.Println(str)

	mystr := "abc"
	fmt.Println("hello, world", mystr)
}
