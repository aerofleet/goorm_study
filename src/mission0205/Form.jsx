function Form() {
  let name = '';

  function handleNameChange(e) {
    name = e.target.value;
  }

  function handleReset() {
    name = '';
  }

  return (
    <form onSubmit={e => e.preventDefault()}>
      <input
        placeholder="이름입력"
        value={name}
        onChange={handleNameChange}
      />
      <h1>{name}님 안녕하세요.</h1>
      <button onClick={handleReset}>Reset</button>
    </form>
  );
}