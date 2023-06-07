fun printCipher(arr: Array<CharArray>){                 //function to print the cipher text
    print("Encrypted text: ")
    for(i in arr) {
        for (j in i) {                                  // loop to go through the elements of the array
            if (j != '~') {
                print(j.uppercaseChar())                //printing in uppercase when element is not a tilde
            }
        }
    }
    println("")
}

fun encrypt(key: Int, text: String){                              //function to encrypt plaintext
    val arr = Array(key) { CharArray(text.length) {'~'} }         //initializing 2d array
    var r = 0
    var col = 0
    var flag = true                                               //initializing variables
    for(i in text){                                               //loop to go through each char in string
        arr[r][col] = text[col]                                   //copying plaintext to array in zigzag order
            col += 1                                              //incrementing column value of array
            if(r == key-1)                                        //if bottom row is reached
                flag = false                                      //change flag to start moving up
            else if(r == 0)                                       //if top row is reached
                flag = true                                       //change flag to start moving down

            if(flag)                                              //incr or decrementing row to move in zigzag
                r += 1
            else if(!flag)
                r -= 1
    }

    printCipher(arr)                                              //printing ciphertext
}

fun printPlain(arr: Array<CharArray>, key: Int, length: Int){     //function to print plaintext
    var r = 0
    var col = 0
    var flag = true                                               //initializing variables
    print("Decrypted text: ")
    for(i in 0..length-1){                                        //looping for plaintext length
        print(arr[r][col])
        col += 1                                                  //incrementing array zigzag in zigzag
        if(r == key-1)
            flag = false
        else if(r == 0)
            flag = true

        if(flag)
            r += 1
        else if(!flag)
            r -= 1
    }
    println("")
}

fun decrypt(key: Int, text: String){                            //function to decrypt ciphertext
    val arr = Array(key) { CharArray(text.length) {'~'} }
    var r = 0
    var col = 0
    var flag = true                                             //initialzing variables
    for(i in text){                                             //looping for ciphertext
        arr[r][col] = '`'                                       //replacing elements in array with '`' in zigzag order
        col += 1
        if(r == key-1)
            flag = false
        else if(r == 0)
            flag = true

        if(flag)
            r += 1
        else if(!flag)
            r -= 1
    }
    var count = 0
    for(i in 0..key-1){
        for(j in 0..text.length-1){                             //looping for array
            val x = arr[i][j]
            if(x == '`') {                                      //replacing elements in zigzag indices with ciphertext chars
                arr[i][j] = text[count]
                count++
            }
        }

    }
    printPlain(arr, key, text.length)                           //calling function to print plaintext
}

fun main(args: Array<String>){                                  //main function
    do {
        println("Choose an option: ")                           //printing menu
        println("1. Encrypt")
        println("2. Decrypt")
        println("3. Exit")
        val ch = readLine()!!.toInt()                           //user input for menu
        if(ch == 1) {
            println("Enter plaintext to encrypt:")
            val plaintext = readLine().toString()
            println("Enter key:")
            var key = readLine()!!.toInt()                      //taking user input for plaintext and key
            encrypt(key, plaintext)                             //calling function to encrypt plaintext
        }
        else if(ch ==2 ){
            println("Enter ciphertext to decrypt:")
            val ciphertext = readLine().toString()
            println("Enter key:")
            var key = readLine()!!.toInt()                      //taking user input for plaintext and key
            decrypt(key, ciphertext)                            //calling function to decrypt ciphertext
        }
        else if(ch == 3)                                        //quit
            break;
        else
            println("Invalid option.")
    }while(ch != 3)
}