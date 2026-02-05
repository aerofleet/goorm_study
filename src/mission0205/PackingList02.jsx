function Item({ name, importance }) {
  return (
    <li className="item">
      {name} - 중요도 : {importance}
    </li>
  );
}

function PackingList() {
  return (
    <section>
      <h1>홍길동의 포장목록</h1>
      <ul>
        <Item importance={9} name="핸드폰" />
        <Item importance={0} name="노트북" />
        <Item importance={6} name="냉장고"/>
      </ul>
    </section>
  );
}