package com.example.binkotlin

import android.app.ProgressDialog
import android.os.AsyncTask
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL


class MainActivity() : AppCompatActivity() {

    lateinit var EnterBin: EditText // Создаём переменные
    var Okbutton: Button? = null
    var Output: TextView? = null
    val binUrl = ("https://lookup.binlist.net/") //  + EnterBin

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        EnterBin = findViewById(R.id.editTextNumber) // Находим элементы по id
        Okbutton = findViewById(R.id.button) // Ок кнопка
        Output = findViewById(R.id.textView) // Вывод данных

        abstract class MyAsyncTasks : AsyncTask<String, String, String>() { // в джаве было стринги
            // Вся ваша сетевая логика
            // должно быть здесь
            //@Override
            override fun onPreExecute() {
                //fun onPreExecute(); Это походу наследия джава и ненадо
                // display a progress dialog to show the user what is happening
                // display a progress dialog for good user experiance
                setContentView(R.layout.activity_main)
                title = "KotlinApp"
                val progressDialog = ProgressDialog(this@MainActivity)
                progressDialog.setTitle("Kotlin Progress Bar")
                progressDialog.setMessage("Application is loading, please wait")
                progressDialog.show()
                progressDialog.setCancelable(false)

                //@Override
                fun doInBackground(vararg params: String)  { // может быть String с вопросом ?
                    // Fetch data from the API in the background.
                    var result = ""
                    try {
                        val url: URL
                        var urlConnection: HttpURLConnection? = null
                        try {
                            url = URL(binUrl)
                            //open a URL connection
                            urlConnection = url.openConnection() as HttpURLConnection
                            val `in`: InputStream = urlConnection.getInputStream()
                            val isw = InputStreamReader(`in`)
                            var data: Int = isw.read()
                            while (data != -1) {
                                result += data.toChar()
                                data = isw.read()
                            }

                            // return the data to onPostExecute method
                            return
                        } catch (e: Exception) {
                            e.printStackTrace()
                        } finally {
                            if (urlConnection != null) {
                                urlConnection.disconnect()
                            }
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                        return //"Exception: " + e.message
                    }
                    return //result
                // Fetch data from the API in the background.
                }

                //@Override
                fun onPostExecute(vararg strings: String) { // убрал s из String s
                    // show results
                }
            }
        }
    }
}




/*
  class MyTask :
            AsyncTask<Void?, Void?, Void?>() {
            // All your networking logic
            // should be here
            override fun doInBackground(vararg params: Void?): Void? {
                val url = URL("https://lookup.binlist.net/" + EnterBin)

                // Тут идут заголовки запросов
                val myConnection: HttpsURLConnection = url.openConnection() as HttpsURLConnection
                myConnection.setRequestProperty("User-Agent", "my-rest-app-v0.1"); // тут видать мы "представляемся"

                if (myConnection.responseCode == 200) {
                    // Успех
                    // Дальнейшая обработка здесь
                } else {
                    // Error handling code goes here
                    var responseBody = null
                    val responseBodyReader = InputStreamReader(responseBody, "UTF-8")
                    val jsonReader = JsonReader(responseBodyReader)

                    jsonReader.beginObject(); // Начать обработку объекта JSON
                    while (jsonReader.hasNext()) { // Перебрать все ключи
                        String key = jsonReader.nextName(); // Получить следующий ключ
                        if (key.equals("organization_url")) { // Проверьте, нужен ли ключ
                            // Получить значение в виде строки
                            String value = jsonReader.nextString();

                            // Сделайте что-нибудь со значением
                            // ...

                            break; // Выход из цикла
                        } else {
                            jsonReader.skipValue(); // Пропустить значения других ключей
                        }
                    }
                } // надо сделать возврат и ошибка уйдет
        }

//        Okbutton.setOnClickListener {
//            if(EnterBin.text.toString().trim().equals("")){
//                Toast.makeText(applicationContext, R.string.attention, Toast.LENGTH_SHORT).show();
//            } else {
//
//            }
//        }

*/