class CURL(
            etNamaLengkap: EditText,
            etDepartemen: EditText,
            etJabatan: EditText,
            etNoHP: EditText,
            etEmail: EditText,
            var progressBarHolderRegister: ConstraintLayout,
            var context: Context
    ) : AsyncTask<Void, Void, String>() {

        val namaLengkap = "Nama=" + etNamaLengkap.text.toString()
        val departemen =  "%0ADepartemen=" + etDepartemen.text.toString()
        val jabatan = "%0AJabatan=" + etJabatan.text.toString()
        val noHP = "%0ANHPp=" + etNoHP.text.toString()
        val email = "%0AEmail=" + etEmail.text.toString()
        var returnValue: Int? = null
        val baseURL = "https://api.telegram.org/bot1115531097:AAHOgChELkW3Kk2PtC1VvOt4BbhlZYju8l8/sendMessage?parse_mode=markdown&chat_id=-397663601&text="
        val command = "curl POST $baseURL$namaLengkap$departemen$jabatan$noHP$email"
        val process: Process = getRuntime().exec(command)

        override fun doInBackground(vararg params: Void?): String? {
            process.inputStream.read()
            process.waitFor()
            return null
        }

        override fun onPreExecute() {
            super.onPreExecute()
            progressBarHolderRegister.visibility = View.VISIBLE

        }

        override fun onPostExecute(result: String?) {
            process.inputStream.close()
            returnValue = process.exitValue()
            super.onPostExecute(result)
            progressBarHolderRegister.visibility = View.GONE
            if (returnValue == 0){
                AlertDialogUtility.alertDialog(context, "Data telah masuk, tunggu konfirmasi dari admin kami", "success.json")
            } else {
                AlertDialogUtility.alertDialog(context, "Registrasi gagal, gunakan jaringan yang stabil untuk registrasi", "warning.json")
            }
        }
    }