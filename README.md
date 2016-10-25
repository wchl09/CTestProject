# CTestProject
AndroidStudio2.2 NDK开发示例
#Android Studio2.2版本NDK开发

在Android Studio2.2之后Google简化了NDK开发的流程，不再需要之前的.mk文件。

[Add C and C++ Code to Your Project](https://developer.android.com/studio/projects/add-native-code.html)

## 1、下载NDK 和 Build Tools

###需要下载的插件：
* NDK:Android的C和C++支持库，必选

* CMake:打包时编译C和C++的工具，必选

* LLDB:在Android Studio中调试C和C++的工具，可选

###使用SDK Manager来下载这三个工具
1. 在Android Studio中，找到菜单栏选择 *Tools > Android > SDK Manager*。
2. 选择*SDK Tools*标签。
3. 选中*LLDB，CMake*和*NDK*。
4. 点击*Apply*，在弹出的对话框中再点击*OK*。
5. 等待安装完成，点击*Finish*，再点击*OK*。

###创建一个新的支持C/C++的项目
和创建一个普通的项目一样，但是在中间的步骤要选择支持C/C++：

1. 在**Configure your new project**向导页时，要勾选**Include C++ Support**选项
2. 在**Customize C++ Support**向导页时，可以自定义项目不同选项：

 * **C++ Standard**:默认的配置
 * **Exceptions Support**:启用C++异常处理，在*cppFlags*增加了*- fexceptions*标志。
 * **Runtime Type Information Support**:启用RTTI，在*cppFliags*中增加*-frtti*标志。
 
3. 完成之后项目有自动支持了C和C++。

###增加新的C/C++库
和以前的方法类似，需要标明库文件。以前是在Android.mk中添加，现在需要在CMakeLists.txt文件中添加，详细的解释在自动添加的CMakeLists.txt文件中也有说明。

~~~java
	add_library( # Sets the name of the library.
             test-lib

             # Sets the library as a shared library.
             SHARED

             # Provides a relative path to your source file(s).
             # Associated headers in the same location as their source
             # file are automatically included.
             src/main/cpp/test-lib.cpp)
~~~
  其中
  
  * *test-lib*表明了NDK库的名字，对应在Java中**System.loadLibrary("test-lib");**中的**test-lib*。
  * *src/main/cpp/test-lib.cpp*标明了文件的位置。
  * 想添加一个新的库就复制这段代码，再修改相应的字段就行。
  
  ~~~
如果一个库中不止一个.cpp/.c文件，只需要在# file are automatically included.中添加相应的文件就可以了。
~~~
 
