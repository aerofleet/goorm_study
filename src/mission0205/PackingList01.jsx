function Item({ name, isPacked }) {
  return (
    <li className="item">
      {name} - '[포장됨]'
    </li>
  );
}

function PackingList() {
  return (
    <section>
      <h1>홍길동의 포장목록</h1>
      <ul>
        <Item isPacked={true} name="핸드폰" />
        <Item isPacked={true} name="노트북" />
        <Item isPacked={false} name="냉장고"/>
      </ul>
    </section>
  );
}