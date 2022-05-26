import { useHistory } from "react-router-dom";
import JoinAgree from "./JoinAgree";
import classes from "./JoinBox.module.css";
import JoinBasicInput from "./JoinBasicInput";
import JoinAddressInput from "./JoinAddressInput";

import { useState, useRef, useEffect } from "react";

const JoinBox = () => {
  const history = useHistory();

  const [account, setAccount] = useState("");
  const [password, setPassword] = useState("");
  const [name, setName] = useState("");
  const [email, setEmail] = useState("");
  const [birth, setBirth] = useState("");
  const [phone, setPhone] = useState("");
  //const [address, setAddress] = useState("");

  const [zipcode, setZipcode] = useState("");
  const [city, setCity] = useState("");
  const [street, setStreet] = useState("");
  const [addressDetail, setAddressDetail] = useState("");

  const zipcodeChangeHandler = (zipcode) => {
    setZipcode(zipcode);
  };

  const cityChangeHandler = (city) => {
    setCity(city);
  };

  const streetChangeHandler = (street) => {
    setStreet(street);
  };

  const addressDetailChangeHandler = (event) => {
    setAddressDetail(event.target.value);
  };

  useEffect(() => {
    if (city !== "" && street !== "" && zipcode !== "") {
      setAddressIsValid(true);
    }
  }, [city, street, zipcode]);

  const [accountIsValid, setAccountIsValid] = useState(false);
  const [passwordIsValid, setPasswordIsValid] = useState(false);
  const [emailIsValid, setEmailIsValid] = useState(false);
  // const [nameIsValid, setNameIsValid] = useState(false);
  const [birtIsValid, setBirthIsValid] = useState(false);
  const [phoneIsValid, setPhoneIsValid] = useState(false);
  const [addressIsValid, setAddressIsValid] = useState(false);
  const [checkedUniqueAccount, setCheckedUniqueAccount] = useState("");

  const checkAccountValidityHandler = (validity) => {
    setAccountIsValid(validity);
  };
  const checkPasswordValidityHandler = (validity) => {
    setPasswordIsValid(validity);
  };

  const checkEmailValidityHandler = (validity) => {
    setEmailIsValid(validity);
  };

  const checkBirthValidityHandler = (validity) => {
    setBirthIsValid(validity);
  };

  const checkPhoneValidityHandler = (validity) => {
    setPhoneIsValid(validity);
  };

  const checkAddressValidityHandler = (validity) => {
    setAddressIsValid(validity);
  };

  const memberInfo = useRef(null);
  const termsInfo = useRef(null);

  // useEffect(() => {
  //   if (accountIsUnique) {
  //     onSetUniqueAccount(enteredId);
  //   }
  // }, [props.accountIsUnique, enteredId]);

  async function checkUniqueHandler(accountInfo) {
    const requestOptions = {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(accountInfo),
    };

    try {
      fetch("/checkUniqueAccount", requestOptions)
        .then((response) => (response.status === 200 ? response.json() : null))
        .then((result) => {
          result
            ? (function () {
                alert("사용가능한 아이디 입니다.");
                setCheckedUniqueAccount(accountInfo.account);
              })()
            : alert("중복된 아이디 입니다.");

          //setAccountIsUnique(result);
        });
    } catch (error) {
      console.log(error);
    }
  }

  const uniqueIdHandler = (uniqueId) => {
    setCheckedUniqueAccount(uniqueId);
  };

  const submitHandler = (event) => {
    event.preventDefault();

    const validityList = [
      accountIsValid,
      passwordIsValid,
      emailIsValid,
      birtIsValid,
      phoneIsValid,
      addressIsValid,
    ];

    const validityItemList = [
      "계정",
      "비밀번호",
      "이메일",
      "생년월일",
      "휴대폰번호",
      "주소",
    ];

    if (checkedUniqueAccount !== account) {
      alert("아이디 중복을 확인해주세요.");
      return;
    }

    const memberInfoData = new FormData(memberInfo.current);

    let index = 0;

    for (let pair of memberInfoData.entries()) {
      console.log(pair[0] + " : " + pair[1]);

      if (index < 6 && !validityList[index]) {
        alert(validityItemList[index] + "(을)를 확인해주세요.");
        return;
      }
      index++;
    }

    let terms = {};
    const termsInfoData = new FormData(termsInfo.current);
    termsInfoData.forEach(function (value, key) {
      terms[key] = value;
    });

    // console.log(JSON.stringify(terms));
    // console.log(terms["terms[0][termsCode]"]);

    const data = {
      memberRegisterRequest: {
        account: account,
        password: password,
        email: email,
        name: name,
        birth: birth,
        phone: phone,
        address: {
          city: city,
          street: street,
          address_detail: addressDetail,
          zipcode: zipcode,
        },
      },
      terms: [
        {
          termsCode: terms["terms[0][termsCode]"],
          agreeYn: terms["terms[0][agreeYn]"],
        },
        {
          termsCode: terms["terms[1][termsCode]"],
          agreeYn: terms["terms[1][agreeYn]"],
        },
      ],
    };

    console.log(JSON.stringify(data));

    joinHandler(data);
  };

  async function joinHandler(joinInfo) {
    const requestOptions = {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(joinInfo),
    };

    try {
      fetch("/joinMember", requestOptions).then((response) => {
        if (response.status === 201) {
          alert("회원가입이 완료되었습니다.");
          history.push("/login");
        } else {
          alert("가입 중 에러가 발생했습니다. 관리자에게 문의 바랍니다.");
        }
      });
    } catch (error) {
      console.log(error);
    }
  }

  let joinForm = (
    <div>
      <form
        id="memberInfo"
        ref={memberInfo}
        className={classes.member_info_form}
      >
        <JoinBasicInput
          id="account"
          name="account"
          labelContent="Account"
          type="text"
          regex={/^(?=.*\d)(?=.*[a-zA-Z])([a-zA-Z0-9]{6,10}$)/}
          regexMessage="영문,숫자 포함 6~10자 이내"
          hasButton={true}
          placeholder="계정을 입력해주세요."
          onUniqueAccountCheck={checkUniqueHandler}
          checkedUniqueAccount={checkedUniqueAccount}
          onSetUniqueAccount={uniqueIdHandler}
          validity={accountIsValid}
          onValidityCheck={checkAccountValidityHandler}
          enteredId={account}
          setEnteredId={setAccount}
        />
        <JoinBasicInput
          id="password"
          name="password"
          labelContent="Password"
          type="password"
          regex={
            /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$&()\-`.+,"])([\w!@#$&()\-`.+,"]{8,12}$)/
          }
          regexMessage="대소문자,숫자,특수문자 포함 8~12자 이내"
          placeholder="비밀번호를 입력해주세요."
          validity={passwordIsValid}
          onValidityCheck={checkPasswordValidityHandler}
          enteredId={password}
          setEnteredId={setPassword}
        />
        <JoinBasicInput
          id="email"
          name="email"
          labelContent="Email"
          type="text"
          regex={/^[\w-\.]+@([\w-]+\.)+[\w-]{2,4}$/}
          regexMessage="유효하지 않은 이메일 형식"
          placeholder="이메일 주소를 입력해주세요."
          validity={emailIsValid}
          onValidityCheck={checkEmailValidityHandler}
          enteredId={email}
          setEnteredId={setEmail}
        />
        <JoinBasicInput
          id="name"
          name="name"
          labelContent="Name"
          type="text"
          placeholder="이름을 입력해주세요."
          enteredId={name}
          setEnteredId={setName}
          // validity={nameIsValid}
        />
        <JoinBasicInput
          id="birth"
          name="birth"
          labelContent="Birth"
          type="text"
          regex={/^[0-9]{6}$/}
          regexMessage="생년월일 예시) 930126"
          placeholder="생년월일을 입력해주세요."
          validity={birtIsValid}
          onValidityCheck={checkBirthValidityHandler}
          enteredId={birth}
          setEnteredId={setBirth}
        />
        <JoinBasicInput
          id="phone"
          name="phone"
          labelContent="Phone"
          type="text"
          regex={/^01([016789])\d{8}$/}
          regexMessage=" '-' 제외 휴대폰 번호"
          placeholder="휴대폰 번호를 입력해주세요."
          validity={phoneIsValid}
          onValidityCheck={checkPhoneValidityHandler}
          enteredId={phone}
          setEnteredId={setPhone}
        />
        <JoinAddressInput
          id="address"
          validity={addressIsValid}
          onValidityCheck={checkAddressValidityHandler}
          zipcode={zipcode}
          city={city}
          street={street}
          onChangeZipcode={zipcodeChangeHandler}
          onChangeCity={cityChangeHandler}
          onChangeStreet={streetChangeHandler}
          onChangeAddressDetail={addressDetailChangeHandler}
        />
      </form>
      <form id="termsInfo" ref={termsInfo} className={classes.terms_info_form}>
        <JoinAgree
          agree_category="SMS 수신"
          code_name="terms[0][termsCode]"
          code_value="smsAgree"
          yes_id="agreeYn_sms_y"
          term_name="terms[0][agreeYn]"
          label_y_value="동의함"
          no_id="agreeYn_sms_n"
          label_n_value="동의하지 않음"
        />
        <JoinAgree
          agree_category="Email 수신"
          code_name="terms[1][termsCode]"
          code_value="emailAgree"
          yes_id="agreeYn_email_y"
          term_name="terms[1][agreeYn]"
          label_y_value="동의"
          no_id="agreeYn_email_n"
          label_n_value="동의하지 않음"
        />
      </form>
      <div className={classes.submit_button_wrap}>
        <button className={classes.submit_button} onClick={submitHandler}>
          Submit
        </button>
      </div>
    </div>
  );

  return (
    <div className={classes.join_form_container}>
      <h1 className={classes.join_title}>Sign Up</h1>
      {joinForm}
    </div>
  );
};

export default JoinBox;
