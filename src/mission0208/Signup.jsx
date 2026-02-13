import { useState } from 'react';

const Signup = () => {
    const [formData, setFormData] = useState({ email: '', password: '', name: ''});
    const [errors, setErrors] = useState({});

    const validate = () => {
        const newErrors = {};
        if (!/\S+@\S+\.\S+/.test(formData.email)) newErrors.email = "유효한 이메일 형식으로 입력하세요.";
        if (formData.password.length < 8) newErrors.password = "비밀번호는 8자리 이상 입력하세요.";
        if (!formData.name.trim()) newErrors.name = "이름을 입력하세요.";
        return newErrors;
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        const valErrors = validate();
        if (Object.keys(valErrors).length > 0) {
            setErrors(valErrors);
            return;
        }    

        const checkRes = await fetch(`http://localhost:4000/users?email=${formData.email}`);
        const existingUser = await checkRes.json();

        if (existingUser.length > 0) {
            alert("이미 존재하는 이메일입니다.");
            return;
        }

        const res = await fetch('http://localhost:4000/users', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(formData),
        });

        if (res.ok) {
            alert("회원가입이 완료되었습니다.");
            setFormData({ email: '', password: '', name: ''});
        }
    };

    return (
        <div style={formStyle}>
            <h2>회원가입</h2>
            <form onSubmit={handleSubmit}>
                <input type="text" placeholder='Name' value={formData.name} onChange={(e) => setFormData ({...formData, name: e.target.value})} />
                {errors.name && <p style={errorStyle}>{errors.name}</p>}

                <input type="email" placeholder='email' value={formData.email} onChange={(e) => setFormData ({...formData, email: e.target.value})} />
                {errors.email && <p style={errorStyle}>{errors.email}</p>}

                <input type="password" placeholder='Password' value={formData.password} onChange={(e) => setFormData ({...formData, password: e.target.value})} />
                {errors.password && <p style={errorStyle}>{errors.password}</p>}

                <button type="submit">가입하기</button>
            </form>
        </div>
    );
};

const formStyle = { 
    display: 'flex',
    flexDirection: 'column',
    width: '300px',
    margin: '20px auto'
};
const errorStyle = {
    color: 'red',
    fontSize: '12px'
};

export default Signup;