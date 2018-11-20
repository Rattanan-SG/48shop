<template>
    <div class="ProductDetail">
        <span style="margin: 0 auto 0 220px">
            <router-link to="/home" style="color: #828282">หน้าแรก</router-link>
            <span style="color: #828282">></span>
            <router-link :to="{ name: 'CategoryProduct', query: { category_name: product.category }}"
             style="color: #828282">{{product.category}}</router-link>
            <span style="color: #828282">></span>
            {{product.name}}
        </span>
        <div class="container" id="product-box">
            <div class="columns">
                <div class="column is-one-fifth" id="item">
                    <img :src=product.image alt="รอซักครู่">
                </div>
                <div class="column">
                    <h1 style="font-size: 24px; margin-top: 20px;">{{product.name}}</h1>
                    <h1 class="field" id="price" style="padding:12px; padding-left: 26px  ">{{product.price+"  บาท"}}</h1> 
                    <div class="field is-grouped " style="color:#9C9CA1;">
                        <h1>จัดส่งโดย เคอรี่</h1>
                        <h1 style="margin-left: auto; margin-right: 65px">20 บาท</h1>   
                    </div>
                    <div class="field is-grouped" style="border:none;">
                        <p style="margin-top: 65px; margin-right: 25px; ">จำนวน : </p>
                    <div class="field has-addons" style="margin-top: 60px;">
                        <div class="control" >
                            <a  id="quantity" class="button is-info" @click="DecreaseQty" :disabled="isDisabledDecrease" style="background-color:#9B9B9B; ">
                            -
                            </a>
                        </div>                  
                        <input style="width: 40px; hight:40px; text-align: center" type="text" :value="product.qty " readonly>
                        <div class="control">
                            <a  id="quantity" class="button is-info" @click="IncreaseQty" :disabled="isDisabledIncrease" style="background-color:#9B9B9B; ">
                            +
                            </a>
                        </div>
                    </div>
                    </div>
                    <div class="button" @click="buy()" :disabled="showNavBot" 
                    style="background-color:#714EC9; color:white;   border:none; padding: 30px 80px; margin-top: 10px; border-radius: 10px;  margin-bottom: 26px;"> <strong>ซื้อสินค้า </strong></div>
                </div>
            </div>
        </div>
        <div class="container" id="detail-box">
            <p><strong>รายละเอียดสินค้า</strong></p>
            <pre id="detail">{{ product.detail}}</pre>
        </div>
         <div class="container" id="detail-mock-box">
        </div>
        <div class="navbar is-fixed-bottom" id="nav-bot" v-if="showNavBot" style="height: 430px;">
            <div class="container" id="nav-box" >
                <div class="columns">
                    <div class="column">
                        <ol type="none" > 
                            <p>สินค้า: {{product.name}}</p>
                            <div class="field is-grouped " id="form">
                            <p style="margin-top: 10px;">ส่งสินค้าไปที่:</p>
                            <button class="button is-small is-rounded" id="small-button" @click="showAddressModal"
                                :disabled="!hasAddress">เปลี่ยนที่อยู่</button>
                            </div>
                            <template v-if="hasAddress">
                                <p class="is-size-7">
                                   <pre id="address" style="background-color: white; margin-left: -30px; width: 800px;">{{
                                       "ชื่อผู้รับ          :    "+address.receiver_name +
                                       "\nจัดส่งไปที่      :    "+address.receiver_address +
                                       "\nจังหวัด          :    "+address.receiver_province +
                                       "\nรหัสไปรษณีย์ :    " + address.receiver_postcode +
                                       "\nโทรศัพท์       :    " +address.tel_no + " "
                                   }}</pre>
                               </p>
                            </template>
                            <template v-else>
                                <a @click="showAddressModal" class="is-size-7">+ ใส่ที่อยู่</a>
                            </template>  
                            <div class="field is-grouped " id="form">
                            <p style="margin-top: 13px;"> ชำระเงินด้วย:</p>
                            <button class="button is-small is-rounded" id="small-button" @click="showCreditModal"
                                :disabled="!hasCredit">เปลี่ยนบัตร</button>
                            </div>
                            <template v-if="hasCredit">
                                <a @click="showCreditModal">
                                    <p class="is-size-7">
                                        {{ "XXXX XXXX XXXX X" +credit.id.charAt(13)+credit.id.charAt(14)+credit.id.charAt(15) }}
                                    </p>
                                </a>
                            </template>
                            <template v-else>
                                <a @click="showCreditModal" class="is-size-7">+ ใส่บัตรเครดิต</a>
                            </template>  
                        </ol> 
                    </div>
                    <div class="column" style="height:20px ; width:200px ;  margin-left: 120px;">
                        <div class="row" style="color:#626567"> ค่าสินค้า : </div>
                        <div class="row" style="color:#626567"> ค่าจัดส่ง : </div>
                            <div class="row" style="margin-top:60px;">ยอดค่าสินค้า :  </div>
                    </div>
                    <div class="column" style="height:20px; width:50px; margin-right: -70px; margin-left: 110px
                    text-align: right; padding-right: 50px">
                        <div class="row" style="color:#626567">  {{product.total}}   </div>
                        <div class="row" style="color:#626567; padding-left: 27px"> 20  </div> 
                        <div class="row" style="margin-top:60px">{{product.total + 20}}  </div>
                    </div>
                        <div class="column" style="height:20px ; width:150px ; margin-left: 0px;">
                        <div class="row" style="color:#626567">   บาท </div>
                        <div class="row" style="color:#626567"> บาท </div> 
                        <div class="row" style="margin-top:60px;">บาท </div>
                    </div>
                    <a class="button is-primary" @click="orderProduct" :disabled="!hasAddress || !hasCredit"
                    style=" padding: 20px 50px; margin-left: -300px;margin-top:160px ">
                        <strong>ยืนยันการซื้อ</strong>
                    </a>
                </div>
            </div>
        </div>
        <div class="modal-card" id="popUpAddress">
            <div class="container" >
                <div class="modal" id="page-modal2" v-bind:style="{display: showAddress}">
                    <div class="modal-background"></div>
                    <section class="modal-card-body">
                        <div class="modal-content" id="screenAddress">
                            <form>
                            <header id ="head" class="modal-card-head">
                                <p class="modal-card-title" style="text-align: center">ส่งสินค้าไปที่</p>
                                <button class="delete"  aria-label="close" @click.prevent="closeAddressModal"></button>
                            </header>
                            <div class="columns is-multiline" style=" display: flex;" id="bodyPopUpAddress">
                                <div class="field is-horizontal" id="addressbox">
                                    <div class="field" >
                                        <label class="label" id="text">ชื่อ - นามสกุล</label>
                                        <div class="control">
                                            <input class="input" type="text" placeholder="Ex. กิตฮับ ขยันอัพจัง" style="width: 300px;"
                                            v-model="address.receiver_name">
                                        </div>
                                    </div>
                                    <div class="field" style="margin-left: 60px;">
                                        <label class="label" id="text">หมายเลขโทรศัพท์ (สำหรับติดต่อ)</label>
                                        <div class="control">
                                            <input class="input" type="text" placeholder="Ex. 062-0672928 " style="width: 300px;" 
                                            v-model="address.tel_no">
                                        </div>
                                    </div>
                                </div>
                                <div class="field" id="addressbox-line2">
                                    <label class="label" id="text" >ที่อยู่ในการจัดส่ง</label>
                                    <div class="control">
                                        <textarea class="textarea" id="address" placeholder="Ex. เช่น 112/44 ตึกหอสมุด ด้านใน KMUTT ถนนสุขสบายดี ตำบลมะละกอ" style="height: 100px; width: 300px;"
                                        v-model="address.receiver_address"></textarea>
                                    </div>
                                </div>
                                <div class="field" style="margin-left: 400px; margin-top: 100px;">
                                    <label class="label" id="text">จังหวัด</label>
                                    <div class="control">
                                        <input id="province" class="input" type="text" placeholder="Ex. กรุงเทพมหานคร " style="width: 300px; height: 50px;"
                                        v-model="address.receiver_province">
                                    </div>
                                </div>
                                <div class="field" style="margin-left: 400px; margin-bottom: 2000px;">
                                    <label class="label" id="text">หมายเลขไปรษณีย์</label>
                                    <div class="control">
                                        <input id="postcode" class="input" type="text" placeholder="Ex. 10140 " style="width: 300px; height: 50px;"
                                        v-model="address.receiver_postcode">
                                    </div>
                                </div>          
                            </div>
                            <div class="field is-grouped" id="buttonAddress">
                                <footer id= "footer" class="modal-card-foot" >
                                    <button class="button" @click.prevent="closeAddressModal" style="width: 100px;">ยกเลิก</button>
                                    <button class="button is-success" @click.prevent="setAddress" style="width: 100px; margin-left: 10px;">บันทึก</button>
                                </footer>
                            </div>
                            </form>
                        </div>
                    </section> 
                </div>
            </div>
        </div>
        <div class="modal-card" id="popUpCradit">
            <div class="container" >
                <div class="modal" id="page-modal" v-bind:style="{display: showCredit}">
                    <div class="modal-background"></div>
                    <section class="modal-card-body">
                        <div class="modal-content" id="screen" style="width: 650px;">
                            <header id ="head" class="modal-card-head">
                                <p class="modal-card-title" style="text-align: center;">ใส่บัตรเครดิต/เดบิต</p>
                                <button class="delete"  aria-label="close" @click.prevent="closeCreditModal"></button>
                            </header>
                             <form                            >
                            <p v-if="errors.length">
                                <ul>
                                    <li v-for="error in errors" :key="error.id" style="color: red; background-color: white; text-align: center;">{{error}}</li>
                                </ul>
                            </p>
                            <div class="columns is-multiline" style=" display: flex; width: 650px; height: 300px;" id="bodyPopUp">
                            <div class="field is-grouped" id="card-Pop" >
                                <div class="field is-grouped" id="number" >
                                    <div class="field" >
                                    <label  id="text" class="label">หมายเลขบัตร
                                    <img src="../assets/visa.svg" width="20" height="10" style="  margin-left: 10px;">
                                    <img src="../assets/mastercard.svg" width="20" height="10" style="  margin-left: 10px;"></label>
                                        <div class="control" >
                                            <input id="num" class="input" type="text" placeholder="Ex. 2321 2342 2415 5432"
                                            v-model="credit.id" >
                                        </div>
                                    </div>
                                </div>
                                <div class="field" id="name" style=" margin-top: 18px;  width: 220px; ">
                                    <label class="label" id="text">ชื่อที่ปรากฏบนบัตร</label>
                                    <div class="control">
                                        <input class="input" type="text" placeholder="Ex. โสภณ จำปาซ่อนกลิ่น" 
                                        v-model="credit.name">
                                    </div>
                                </div>
                            </div>
                                <div class="field is-grouped " id="MY">
                                    <div class="field" id="month">
                                        <label  id="text" class="label">เดือนหมดอายุ</label>
                                        <div class="control ">
                                            <input id="box" class="input" type="text" placeholder="mm" style="width: 150px;  margin-right: 50px;"
                                            v-model="credit.exp_m">
                                        </div>
                                    </div>
                                    <div class="field" id="year">
                                        <label  id="text" class="label">ปีหมดอายุ</label>
                                        <div class="control">
                                            <input id="box" class="input" type="text" placeholder="yyyy" style="width: 130px;  margin-right: 50px;"
                                            v-model="credit.exp_y">
                                        </div>
                                    </div>
                                    <div class="field" >
                                        <label  id="text" class="label ">รหัสรักษาความปลอดภัย</label>
                                        <div class="control">
                                            <input id="box" class="input" type="text" placeholder="CVV" style="width: 110px;  margin-right: 50px;"
                                            v-model="credit.cvv">
                                        </div>
                                    </div>
                                </div>
                                    <div class="field is-grouped " id="foot"> 
                                      <div class="field" >
                                            <button class="button" @click.prevent="closeCreditModal">ยกเลิก</button>
                                            <button class="button is-success" @click.prevent="checkForm">บันทึก</button>
                                      </div>
                                    </div>
                                </div>    
                            </form>
                             <!-- form end -->    
                        </div>
                    </section>
                </div>
            </div>
        </div>    
    </div>
</template>

<script>
import './../../node_modules/bulma/css/bulma.css';
import axios from 'axios';
import chunk from 'chunk';

const url_test = `http://jsonplaceholder.typicode.com/posts`;
const url_product = `http://localhost:8080/product/`;
const url_credit = `http://localhost:8080/creditcard`;
const url_order = `http://localhost:8080/order`;

export default {
    name: 'ProductDetail',
    data () {
        return {
            msg: 'This is Detail page',
            product: {
                id: 0,
                category: '',
                name: '',
                price: '',
                image: '',
                detail: '',
                qty: 1,
                total: ''
            },
            isDisabledDecrease: true,
            isDisabledIncrease: false,
            showNavBot: false,
            showCredit: '',
            showAddress: '',
            hasAddress: false,
            hasCredit: false,
            credit: {
                id: '',
                exp_m: '',
                exp_y: '',
                cvv: '',
                name: '',
                token: '',
                message: ''
            },
            address: {
                receiver_name: '',
                tel_no: '',
                receiver_address: '',
                receiver_province: '',
                receiver_postcode: ''
            },
            errors: [] 
        }
    },
    created() {
        this.product.id = this.$route.params.id;
    },
    mounted() {
        Omise.setPublicKey("pkey_test_5dviz6scp4tdk4cm0au")
        this.getProductDetail();
    },
    methods: {
        IncreaseQty: function () {
            if (this.product.qty >= 9) {
                this.isDisabledIncrease = true;
                if (this.product.qty === 9) {
                    this.product.qty++;
                    this.product.total = this.product.price * this.product.qty;
                }     
            } else {
                this.product.qty++;
                this.product.total = this.product.price * this.product.qty;
                this.isDisabledDecrease = false;
            }   
        },
        DecreaseQty: function () {
            if (this.product.qty <= 2) {
                this.isDisabledDecrease = true;
                if (this.product.qty === 2) {
                    this.product.qty--;
                    this.product.total = this.product.price * this.product.qty;
                }
            } else {
                this.product.qty--;
                this.product.total = this.product.price * this.product.qty;
                this.isDisabledIncrease = false;
            }  
        },
        getProductDetail: function() {
            axios.get(url_product + this.product.id)
            .then(response => {
                this.product.category = response.data.category.name
                this.product.name = response.data.name,
                this.product.price = response.data.price,
                this.product.image = response.data.img_url,
                this.product.detail = response.data.detail,
                this.product.total = response.data.price
            })
        },
        buy: function() {
            this.showNavBot = true;    
        },
        showAddressModal: function() {
            this.showAddress = 'block';         
        },
        closeAddressModal: function() {
            this.showAddress = '';
        },
        showCreditModal: function() {
            this.showCredit = 'block';
        },
        closeCreditModal: function() {
            this.showCredit = '';
        },
        setAddress: function () {
            this.hasAddress = true;
            this.showAddress = '';
        },
        censorCreditCard: function() {
            credit.id.toString();
        },
        orderProduct: function() {
            axios.post(url_order, {
                userProfile: {
                    firstname: "Tanapat",
                    lastname: "Choochot"
                },
                startLocation: {
                    receiverName: '48shop',
                    detail: "48Shop 1/2 Surin Thepkanjana Rd, Khok Krabue",
                    city: "Samut Sakhon",
                    district: "Amphoe Mueang Samut Sakhon",
                    zipcode: 74000,
                    telNumber: '025453227'
                },
                destination: {
                    receiverName: this.address.receiver_name,
                    detail: this.address.receiver_address,
                    city: this.address.receiver_province,
                    zipcode: this.address.receiver_postcode,
                    telNumber: this.address.tel_no
                },
                product_id: this.product.id,
                productQuantity: this.product.qty,
                totalPrice: this.product.total,
                trackingId: 123456789,
                method: "Credit card",
                omiseToken: this.credit.token,                        
            })
            .then(response => {
                console.log(response.data);
            })
        },
        creditCardToken: function(){
            const card = {
                number: this.credit.id,
                expiration_month: this.credit.exp_m,
                expiration_year: this.credit.exp_y,
                security_code: this.credit.cvv,
                name: this.credit.name
            }
            console.log(Omise) 
            Omise.createToken('card',card,(statuscode,response)=>{
                if(statuscode == 200){
                    this.closeCreditModal();
                    console.log(response.id)
                    this.credit.token = response.id;
                    this.credit.message = 'valid card';
                    this.hasCredit= true;
                }
                else{
                    this.hasCredit = false;
                    this.credit.message = response.message;
                    alert(response.message);
                }
            })  
        },
        checkForm: function (e) {
           console.log('CheckForm')
                if (this.credit.id.length > 0 && this.credit.name.length > 0 && this.credit.exp_m.length > 0 && 
                    this.credit.exp_y.length > 0 && this.credit.cvv > 0) {
                this.creditCardToken();
                }   

                this.errors = [];

                if(this.credit.id.length == 0) {
                this.errors.push('Credit Card Number required');
                }

                if(!this.credit.id.length == 0 && this.credit.id.length != 16) {
                this.errors.push('Invalid Card Number');
                }

                if(this.credit.name.length == 0) {
                this.errors.push('Name required');
                }

                if(this.credit.exp_m.length == 0) {
                this.errors.push('Expiry Month required');
                }

                if(!this.credit.exp_m.length == 0 && (this.credit.exp_m < 1 || this.credit.exp_m > 12)) {
                this.errors.push('Invalid Month');
                }

                if(this.credit.exp_y.length == 0 ) {
                this.errors.push('Expiry Year Number required');
                }

                if(!this.credit.exp_y.length == 0 && (this.credit.exp_y < 2018 || this.credit.exp_y > 2025)) {
                this.errors.push('Invalid Expiry Year');
                }

                if(this.credit.cvv.length == 0) {
                this.errors.push('CVV required');
                }

                if(!this.credit.cvv.length == 0 && this.credit.cvv.length != 3) {
                this.errors.push('Invalid CVV');
                }

        }
    }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
#detail{
    background-color:white;
    font-family: "Avenir", Helvetica, Arial, sans-serif;
    color: black;
    margin-left: 20px;

}
#item{
    margin-left: 60px;
    margin-top: 50px;
    margin-right: 40px
}
#price{
    width: 630px;
    height: 60px;
    background-color: #EBEBEB;
    font-size: 24px;
    margin-bottom: 26px;
    margin-top: 26px;
    
}
#quantity{
    width: 40px;
}
#nav-bot {
    height: 270px;
    border: 0.09em solid #E0E0E0;
}
#nav-top {
  border-bottom: 10px;
  border-color: #E0E0E0;
}

#nav-box {
  width: 1000px;
  margin-top: 20px;
}

#product-box {
  background: white;
  width: 1000px;
  min-height: 390px;
  padding-bottom: 13px;
  box-shadow: 0 4px 15px 0 rgba(40,44,53,.06), 0 2px 2px 0 rgba(40,44,53,.08);
  margin-top: 20px;
}

#detail-box {
  background: white;
  width: 1000px;
  height: auto;
  margin-top: 13px;
  box-shadow: 0 4px 15px 0 rgba(40,44,53,.06), 0 2px 2px 0 rgba(40,44,53,.08);
  padding: 50px;
}

#detail-mock-box {
    background: white;
    width: 1000px;
    height: 400px;
    margin-top: 13px;
    box-shadow: 0 4px 15px 0 rgba(40,44,53,.06), 0 2px 2px 0 rgba(40,44,53,.08);
    padding: 50px;
}

html {
    background: #f2f2f2;
}
#num{
    width: 220px;
}

nav {
  background: #f2f2f2;
  border-bottom: 10px;
  border-color: #e0e0e0;
}

#nav-top {
  border-bottom: 10px;
  border-color: #e0e0e0;
}

#nav-box {
  width: 1000px;
}

#form {
    width: 500px;
    height: 35px;
    margin-top: 10px;
}

#card-Pop{
    height: 90px;
    width: 1200px;
    margin-left: 50px;
}
#MY{
    width: 1200px;
    margin-left: 50px
}

#name{
    margin-top: 19px;
    margin-right: 150px;
    padding-right: 30px;
}
#foot{
    width: 800px;
    margin-bottom: 20px;
    justify-content: center;
    margin-top: 10px;
  
}

#bodyPopUp {
  background: white;
  margin-top:0px;
  margin-left: 0px;
  position: fixed;
  height: 250px;
  width: 600px;
  border-bottom-left-radius: 20px;
  border-bottom-right-radius: 20px;
  

}


#screen {
  width: 600px;
  margin-top: 10%;

}

#addressbox {
    margin-top: 20px;
    margin-left: 40px;
    position: fixed;
}

#addressbox-line2 {
    margin-top: 100px;
    margin-left: 40px;
    position: fixed;
}

#bodyPopUpAddress {
    text-align: left;
    background: white;
    margin-top:0px;
    margin-left: 0px;
    position: fixed;
    height: 400px;
    width: 800px;
}

#screenAddress {
  width: 800px;
}

#buttonAddress {
    justify-content: center;
    margin-top: 300px;
    margin-right: 60px;
}
#box {
  width: 40%;
  height: 20%;
}

#text {
  font-size: 9pt;
  font-weight: 100;

}
#number {
    height: 9px;
    margin-right: 80px;
    margin-top: 10px;
}
#head {
    border-style: none;
    margin-top: 20px;
}
#footer {
    margin-left: 20px;
    background-color:white; 
    border-style: none;
}

#small-button {
    margin-left: 20px;
    margin-top: 13px;
}
/* popup1 */
#element-toggle {
  display: none;
}
#element-toggle:checked ~ #popUpCradit {
  display: flex;
}
#box-Popup{
  max-width: 1050px;
  height: 550px;
  background: #E0E0E0;
  border-radius: 10px;
  
}
#cards{
  width: 520px;
  height: 80px;
  margin-left: 60px;
  margin-top: 10px;
  background: white;
}
#buttom{
  padding: 45px;
  margin-left: 27%;
  border: none;
  margin-top: 40%;
  

}
#body{
  margin-top: -35px;
  position: fixed;
}

#table{
  background:#E0E0E0;
  border: none;
  width: 800px;
  margin-left: 90px;
  margin-top: 10px;
  
}
#text-cradit{
font-size: 17px;
margin-top: 10px;
margin-left: 70px;
}
#head-cradit{
  font-size: 20px;
  height: 100px;
}
#address{
    font-size: 10px;
    width: 370px;
}
#text-add{
    font-size: 10px;
    width: 100px;
    margin-right: -25px;
}

</style>

