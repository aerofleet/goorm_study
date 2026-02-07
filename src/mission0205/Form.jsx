import { useState } from 'react';

export default function Form() {
  
  const [name, setName] = useState('');

  function handleNameChange(e) {
    setName(e.target.value);
  }

  function handleReset() {
    setName('');
  }

  return (
    <form onSubmit={e => e.preventDefault()}>
      <input
        placeholder="이름입력"
        value={name}
        onChange={handleNameChange}
      />
      <h1>{name ? `${name}님 안녕하세요.` : '이름을 입력해주세요.'}</h1>
      <button onClick={handleReset}>Reset</button>
    </form>
  );
}