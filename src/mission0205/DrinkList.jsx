function Drink({ name }) {
  return (
    <section>
      <h1>{name}</h1>
      <dl>
        <dt>원료</dt>
        <dd>{name === '녹차' ? '녹차잎' : '커피콩'}</dd>
        <dt>카페인</dt>
        <dd>{name === '녹차' ? '15–70 mg/cup' : '80–185 mg/cup'}</dd>
        <dt>역사</dt>
        <dd>{name === '녹차' ? '4000년 전' : '1000년 전'}</dd>
      </dl>
    </section>
  );
}

function DrinkList() {
  return (
    <div>
      <Drink name="녹차" />
      <Drink name="커피" />
    </div>
  );
}