//package com.example.storageversion2
//
//import android.Manifest
//import android.content.Intent
//import android.content.pm.PackageManager
//import android.os.Build
//import androidx.appcompat.app.AlertDialog
//import androidx.core.app.ActivityCompat.shouldShowRequestPermissionRationale
//import androidx.core.content.ContextCompat
//
//
//private fun startCameraFeature() {
//
//    val permission = Manifest.permission.CAMERA
//    handleCheckResult(
//        permission,
//        checkPermission(permission)
//    )
//}
//
//private fun startItemActivity() {
//    startActivity(Intent(this, ItemActivity::class.java))
//}
//
//private fun checkPermission(permission: String): CheckPermissionResult {
//    return when {
//        Build.VERSION.SDK_INT < Build.VERSION_CODES.M -> CheckPermissionResult.GRANTED
//
//        ContextCompat.checkSelfPermission(
//            this,
//            permission
//        ) == PackageManager.PERMISSION_GRANTED -> CheckPermissionResult.GRANTED
//
//        Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && shouldShowRequestPermissionRationale(
//            permission
//        ) -> CheckPermissionResult.NEED_TO_EXPLAIN
//
//        else -> CheckPermissionResult.NEED_TO_REQUEST
//    }
//}
//
//private fun handleCheckResult(permission: String, result: CheckPermissionResult){
//    when (result){
//        CheckPermissionResult.GRANTED -> startCameraActivity()
//        CheckPermissionResult.DENIED -> failedGracefully()
//        CheckPermissionResult.NEED_TO_REQUEST -> askForPermission(Manifest.permission.CAMERA)
//        CheckPermissionResult.NEED_TO_EXPLAIN -> showRationale()
//    }
//}
//
//private fun showRationale() {
//    AlertDialog.Builder(this)
//        .setTitle("Camera permission")
//        .setMessage("Camera permission is needed to allow this feature work.")
//        .setPositiveButton("I understand") { _, _ -> askForPermission(Manifest.permission.CAMERA) }
//        .show()
//}
//
//private fun failedGracefully() {
//    AlertDialog.Builder(this)
//        .setTitle("Camera permission")
//        .setMessage("Camera Permission was not granted. We respect your decision.")
//        .setNegativeButton("I changed my mind") { _, _ -> askForPermission(Manifest.permission.CAMERA)}
//        .setPositiveButton("Ok", null)
//        .show()
//}
//
//private val requestPermissionLauncher =
//    registerForActivityResult(
//        ActivityResultContracts.RequestPermission()
//    ){ isGranted: Boolean ->
//        if(isGranted){
//            startCameraActivity()
//        } else{
//            failedGracefully()
//        }
//    }
//
//private fun askForPermission(permission: String) {
//    requestPermissionLauncher.launch(permission)
//}
//
//enum class CheckPermissionResult{
//    GRANTED,
//    DENIED,
//    NEED_TO_REQUEST,
//    NEED_TO_EXPLAIN
//}