package br.com.denilson100.viajabessa.asynctask

import android.os.AsyncTask

class BaseAsyncTask<T>(
    private val didExecut: () -> T,
    private val didFinally: (result: T) -> Unit
) : AsyncTask<Void, Void, T>() {

    override fun doInBackground(vararg params: Void?) = didExecut()

    override fun onPostExecute(result: T) {
        super.onPostExecute(result)
        didFinally(result)
    }

}
