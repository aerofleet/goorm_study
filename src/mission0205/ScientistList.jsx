const people = [
  {
    id: 0,
    name: '크레올라 캐서린 존슨',
    profession: '수학자',
    accomplishment: '우주 비행 계산',
  }, {
    id: 1,
    name: '마리오 호세 몰리나',
    profession: '화학자',
    accomplishment: '북극 오존 구멍 발견',
  }, {
    id: 2,
    name: '모하마드 압두스 살람',
    profession: '물리학자',
    accomplishment: '전자기 이론',
  }, {
    id: 3,
    name: '퍼시 라본 줄리안',
    profession: '화학자',
    accomplishment: '코르티손 약물',
  }, {
    id: 4,
    name: '수브라마니안 찬드라세카르',
    profession: '천체 물리학자',
    accomplishment: '백색 왜성 별 질량 계산',
  }
];

export default function ScientistList() {
  const chemists = [];
  const scientists = [];
  
  for (let i = 0; i < people.length ; i++) {
    const person = people[i];
    const personTag = (
      <li key = {person.id}>
        <p>
        <b>{person.name} : </b>
        [{person.accomplishment}]으로 알려진 {' ' + person.profession + ' '}
      </p>
      </li>
    );

    if (person.profession === '화학자') {
      chemists.push(personTag);      
    } else {
      scientists.push(personTag);
    }
  }   

  return (
    <article>
      <h1>과학자</h1>
      <h3>화학자 명단</h3>
      <ul>{chemists}</ul>
      <h3>기타 과학자</h3>
      <ul>{scientists}</ul>      
    </article>
  );
}