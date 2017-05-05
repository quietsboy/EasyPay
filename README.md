# EasyPay
[![](https://www.jitpack.io/v/quietsboy/EasyPay.svg)](https://www.jitpack.io/#quietsboy/EasyPay)


在APP开发过程中，时常会加入支付功能，现在普遍使用的支付方式为：支付宝支付、微信支付和银联支付。本项目即对三种支付进行封装，以便于开发者直接调用。

## 如何添加？
步骤1：将其添加到存储库末尾的根build.gradle中：

    allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}

 步骤2：添加依赖关系
 

    dependencies {
		compile 'com.github.quietsboy:EasyPay:v1.0.0-beta1'
	}

## 如何调用
