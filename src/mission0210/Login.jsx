import { useState } from "react";

const Login = () => {
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');

    const handleLogin = async (e) => {
        e.preventDefault();
        const res = await fetch(`http://localhost:4000/users?email=${email}&password=${password}`);
        const users = await res.json();

        if (users.length > 0) {
            alert(`${users[0].name}님, 오셨군요.`);
        } else {
            alert("로그인 정보를 재확인 바랍니다.");            
        }
    };

    return (
        <div style={formStyle}>
            <h2>로그인</h2>
            <form onSubmit={handleLogin}>
                <input type="email" placeholder="email" value={email} onChange={(e) => setEmail(e.target.value)} required />
                <input type="password" placeholder="password" value={password} onChange={(e) => setPassword(e.target.value)} required />
                <button type="submit">로그인</button>
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

export default Login;